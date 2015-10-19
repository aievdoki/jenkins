package com.avid.hydra.jenkins.dto

import com.avid.hydra.jenkins.api.*
import com.avid.hydra.jenkins.impl.JenkinsBuildInfoBuilder
import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Created by andrii.ievdokimov on 9/28/2015.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class JenkinsJobDTO implements JenkinsJob {

  private final String displayName

  private final String name

  private final String url

  private final boolean buildable

  private final JenkinsBuildInfoDTO lastBuild

  private final JenkinsBuildInfoDTO lastSuccessfulBuild

  private final JenkinsBuildInfoDTO lastUnsuccessfulBuild

  private final int nextBuildNumber

  JenkinsJobDTO(JenkinsBuildInfoDTO lastUnsuccessfulBuild, int nextBuildNumber, String displayName, String name, buildable, JenkinsBuildInfoDTO lastBuild, JenkinsBuildInfoDTO lastSuccessfulBuild, String url) {
    this.lastUnsuccessfulBuild = lastUnsuccessfulBuild
    this.nextBuildNumber = nextBuildNumber
    this.displayName = displayName
    this.name = name
    this.buildable = buildable
    this.lastBuild = lastBuild
    this.lastSuccessfulBuild = lastSuccessfulBuild
    this.url = url
  }

  @Override
  String getDisplayName() {
    displayName
  }

  @Override
  boolean isBuildable() {
    buildable
  }

  @Override
  JenkinsBuildInfo getLastBuild() {
    new JenkinsBuildInfoBuilder().
        setDuration(lastBuild.duration).
        setEstimatedDuration(lastBuild.estimatedDuration).
        setNumber(lastBuild.number).
        setTimestamp(lastBuild.timestamp).
        setUrl(lastBuild.url).
        setIsBuilding(lastBuild.building).
        setStatus(lastBuild.status).
        build()
  }


  @Override
  JenkinsBuildInfo getLastSuccessfulBuild() {
    new JenkinsBuildInfoBuilder().
        setDuration(lastSuccessfulBuild.duration).
        setEstimatedDuration(lastSuccessfulBuild.estimatedDuration).
        setNumber(lastSuccessfulBuild.number).
        setTimestamp(lastSuccessfulBuild.timestamp).
        setUrl(lastSuccessfulBuild.url).
        setIsBuilding(lastSuccessfulBuild.building).
        setStatus(lastSuccessfulBuild.status).
        build()
  }

  @Override
  JenkinsBuildInfo getLastUnsuccessfulBuild() {
    new JenkinsBuildInfoBuilder().
        setDuration(lastUnsuccessfulBuild.duration).
        setEstimatedDuration(lastUnsuccessfulBuild.estimatedDuration).
        setNumber(lastUnsuccessfulBuild.number).
        setTimestamp(lastUnsuccessfulBuild.timestamp).
        setUrl(lastUnsuccessfulBuild.url).
        setIsBuilding(lastUnsuccessfulBuild.building).
        setStatus(lastUnsuccessfulBuild.status).
        build()
  }

  @Override
  int getNextBuildNumber() {
    nextBuildNumber
  }

  @Override
  String getBuildLink() {
    return null
  }

  @Override
  String getName() {
    name
  }

  @Override
  String getUrl() {
    url
  }
}
