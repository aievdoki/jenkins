package com.avid.hydra.jenkins.api.management

import com.avid.hydra.jenkins.api.JenkinsJob

/**
 * Created by andrii.ievdokimov on 9/29/2015.
 */
interface JenkinsHelper {

  JenkinsJob getJobByName(String name)

  List<String> getAllJobs()

}