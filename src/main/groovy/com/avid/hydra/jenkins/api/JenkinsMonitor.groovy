package com.avid.hydra.jenkins.api

/**
 * Created by andrii.ievdokimov on 9/28/2015.
 */
interface JenkinsMonitor {

  JenkinsJob getJob(String name)

  List<String> getAllJobsName()
}