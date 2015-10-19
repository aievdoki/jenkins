package com.avid.hydra.jenkins.impl

import com.avid.hydra.jenkins.dto.JenkinsBuildInfoDTO

/**
 * Created by andrii.ievdokimov on 9/29/2015.
 */
class JenkinsBuildInfoBuilder {

  private int number

  private String url

  private String status

  private long duration

  private long timestamp

  private long estimatedDuration

  private boolean isBuilding

  JenkinsBuildInfoBuilder setIsBuilding(boolean isBuilding) {
    this.isBuilding = isBuilding
    this
  }

  JenkinsBuildInfoBuilder setNumber(int number) {
    this.number = number
    this
  }

  JenkinsBuildInfoBuilder setUrl(String url) {
    this.url = url
    this
  }

  JenkinsBuildInfoBuilder setDuration(long duration) {
    this.duration = duration
    this
  }

  JenkinsBuildInfoBuilder setTimestamp(long timestamp) {
    this.timestamp = timestamp
    this
  }

  JenkinsBuildInfoBuilder setEstimatedDuration(long estimatedDuration) {
    this.estimatedDuration = estimatedDuration
    this
  }

  JenkinsBuildInfoBuilder setStatus(String status) {
    this.status = status
    this
  }

  JenkinsBuildInfoDTO build() {
    new JenkinsBuildInfoDTO(number, url, duration, timestamp, estimatedDuration, isBuilding, status)
  }
}
