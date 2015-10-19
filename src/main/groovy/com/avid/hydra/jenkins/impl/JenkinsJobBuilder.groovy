package com.avid.hydra.jenkins.impl

import com.avid.hydra.jenkins.api.JenkinsJob
import com.avid.hydra.jenkins.dto.JenkinsBuildInfoDTO
import com.avid.hydra.jenkins.dto.JenkinsJobDTO

/**
 * Created by andrii.ievdokimov on 9/29/2015.
 */
class JenkinsJobBuilder {

  private String displayName

  private String name

  private String url

  private boolean buildable

  private JenkinsBuildInfoDTO lastBuild = null

  private JenkinsBuildInfoDTO lastSuccessfulBuild = null

  private JenkinsBuildInfoDTO lastUnsuccessfulBuild = null

  private int nextBuildNumber = 0

  JenkinsJobBuilder setDisplayName(String displayName) {
    this.displayName = displayName
    this
  }

  JenkinsJobBuilder setName(String name) {
    this.name = name
    this
  }

  JenkinsJobBuilder setUrl(String url) {
    this.url = url
    this
  }

  JenkinsJobBuilder setBuildable(boolean buildable) {
    this.buildable = buildable
    this
  }

  JenkinsJobBuilder setLastBuild(JenkinsBuildInfoDTO lastBuild) {
    this.lastBuild = lastBuild
    this
  }

  JenkinsJobBuilder setLastSuccessfulBuild(JenkinsBuildInfoDTO lastSuccessfulBuild) {
    this.lastSuccessfulBuild = lastSuccessfulBuild
    this
  }

  JenkinsJobBuilder setLastUnsuccessfulBuild(JenkinsBuildInfoDTO lastUnsuccessfulBuild) {
    this.lastUnsuccessfulBuild = lastUnsuccessfulBuild
    this
  }

  JenkinsJobBuilder setNextBuildNumber(int nextBuildNumber) {
    this.nextBuildNumber = nextBuildNumber
    this
  }

  JenkinsJob build() {
    new JenkinsJobDTO(lastUnsuccessfulBuild, nextBuildNumber, displayName, name, buildable, lastBuild, lastSuccessfulBuild, url)
  }

}
