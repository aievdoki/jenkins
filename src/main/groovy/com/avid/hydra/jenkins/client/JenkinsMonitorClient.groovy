package com.avid.hydra.jenkins.client

import com.avid.hydra.jenkins.api.JenkinsJob
import com.avid.hydra.jenkins.api.JenkinsMonitor
import com.avid.hydra.jenkins.dto.JenkinsBuildInfoDTO
import com.avid.hydra.jenkins.dto.JenkinsJobDTO
import com.avid.hydra.jenkins.impl.JenkinsBuildInfoBuilder
import com.avid.hydra.jenkins.impl.JenkinsJobBuilder
import groovyx.net.http.RESTClient

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.Method.GET

/**
 * Created by andrii.ievdokimov on 9/28/2015.
 */
class JenkinsMonitorClient implements JenkinsMonitor {

  private final RESTClient rest

  private final MonitorHelper monitorHelper = new MonitorHelper()

  JenkinsMonitorClient(RESTClient rest) {
    this.rest = rest
  }

  JenkinsJob getJob(String name) {
    monitorHelper.getJobByName(name)
  }

  List<String> getAllJobsName() {
    monitorHelper.getJobsName()
  }

  private final class MonitorHelper {

    private static final String JOB_URI = "/job"
    private static final String INTERNAL_JSON_API = "/api/json"

    List<String> getJobsName() {
      HashMap jenkins = doGetRequest(api())
      jenkins.jobs.collect {
        it.name
      }
    }


    JenkinsJobDTO getJobByName(String name) {

      HashMap jobInfo = doGetRequest(api(JOB_URI, name))
      JenkinsJobBuilder jobBuilder = new JenkinsJobBuilder()
      jobBuilder.setDisplayName(jobInfo.displayName).
          setName(jobInfo.name).
          setBuildable(jobInfo.buildable).
          setNextBuildNumber(jobInfo.nextBuildNumber).
          setUrl(jobInfo.url)

      if (jobInfo.lastBuild?.containsKey("url")) {
        jobBuilder.setLastBuild(buildJenkinsBuildInfo(jobInfo.lastBuild.url - rest.uri))
      }
      if (jobInfo.lastStableBuild?.containsKey("url")) {
        jobBuilder.setLastSuccessfulBuild(buildJenkinsBuildInfo(jobInfo.lastStableBuild.url - rest.uri))
      }
      if (jobInfo.lastUnsuccessfulBuild?.containsKey("url")) {
        jobBuilder.setLastUnsuccessfulBuild(buildJenkinsBuildInfo(jobInfo.lastUnsuccessfulBuild.url - rest.uri))
      }
      jobBuilder.build()

    }

    JenkinsBuildInfoDTO buildJenkinsBuildInfo(String url) {
      def buildInfo = doGetRequest(api(url))
      new JenkinsBuildInfoBuilder().
          setDuration(buildInfo.duration).
          setEstimatedDuration(buildInfo.estimatedDuration).
          setNumber(buildInfo.number).
          setTimestamp(buildInfo.timestamp).
          setUrl(buildInfo.url).
          setIsBuilding(buildInfo.building).
          setStatus(buildInfo.result).
          build()
    }

    private HashMap doGetRequest(String path) {
      rest.request(GET, JSON) {
        uri.path = path
      }.getData()
    }

    String api(String... parts) {
      parts.join("/") + INTERNAL_JSON_API
    }


  }

}
