package com.avid.hydra.jenkins.impl.management

import com.avid.hydra.jenkins.api.JenkinsJob
import com.avid.hydra.jenkins.api.JenkinsMonitor
import com.avid.hydra.jenkins.api.management.JenkinsHelper
import com.avid.hydra.jenkins.client.JenkinsMonitorClient
import com.avid.hydra.jenkins.client.RestClientBuilder
import groovyx.net.http.RESTClient

/**
 * Created by andrii.ievdokimov on 9/29/2015.
 */
class JenkinsHelperImpl implements JenkinsHelper {

  private final RESTClient client
  private final JenkinsMonitor monitor

  JenkinsHelperImpl(String url, String userName, String userPassword) {
    client = new RestClientBuilder(url, userName, userPassword).build()
    monitor = new JenkinsMonitorClient(client)
  }

  JenkinsHelperImpl(String url) {
    client = new RestClientBuilder(url).build()
    monitor = new JenkinsMonitorClient(client)
  }

  @Override
  JenkinsJob getJobByName(String name) {
    monitor.getJob(name)
  }

  @Override
  List<String> getAllJobs() {
    monitor.getAllJobsName()
  }

}
