package com.avid.hydra.jenkins.dto

import com.avid.hydra.jenkins.api.JenkinsHealthReport

/**
 * Created by andrii.ievdokimov on 9/29/2015.
 */
class HealthReportDTO implements JenkinsHealthReport {

  private final String description

  private final String iconClassName

  private final String iconUrl

  private final int score

  HealthReportDTO(String description, String iconClassName, String iconUrl, int score) {
    this.description = description
    this.iconClassName = iconClassName
    this.iconUrl = iconUrl
    this.score = score
  }

  @Override
  String getDescription() {
    return description
  }

  @Override
  String getIconClassName() {
    return iconClassName
  }

  @Override
  String getIconUrl() {
    return iconUrl
  }

  @Override
  int getScore() {
    return score
  }
}
