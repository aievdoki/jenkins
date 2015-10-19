package com.avid.hydra.jenkins.dto

import com.avid.hydra.jenkins.api.JenkinsBuildInfo
import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Created by andrii.ievdokimov on 9/29/2015.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class JenkinsBuildInfoDTO implements JenkinsBuildInfo {

  private final int number

  private final String url

  private final String status

  private final long duration

  private final long timestamp

  private final long estimatedDuration

  private final boolean isBuilding

  JenkinsBuildInfoDTO(int number, String url, long duration, long timestamp, long estimatedDuration, boolean isBuilding, String status) {
    this.number = number
    this.url = url
    this.duration = duration
    this.timestamp = timestamp
    this.estimatedDuration = estimatedDuration
    this.isBuilding = isBuilding
    this.status = status
  }

  @Override
  int getNumber() {
    return number
  }

  @Override
  String getURL() {
    return url
  }

  @Override
  long getDuration() {
    return duration
  }

  @Override
  long getTimestamp() {
    return timestamp
  }

  @Override
  long getEstimatedDuration() {
    return estimatedDuration
  }

  @Override
  boolean isBuilding() {
    return isBuilding
  }

  @Override
  String getStatus() {
    return status
  }
}
