package com.avid.hydra.jenkins.impl.job
import com.avid.hydra.jenkins.dto.JenkinsJobDTO
/**
 * Created by andrii.ievdokimov on 9/30/2015.
 */
class HostsAndJobsDTO {
  String name
  String username
  String address
  String password
  List<JenkinsJobDTO> jobs
}
