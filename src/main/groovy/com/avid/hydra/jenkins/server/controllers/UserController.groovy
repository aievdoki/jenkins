package com.avid.hydra.jenkins.server.controllers
import com.avid.hydra.jenkins.api.management.JenkinsCredentials
import com.avid.hydra.jenkins.api.management.User
import com.avid.hydra.jenkins.impl.job.HostsAndJobsDTO
import com.avid.hydra.jenkins.impl.management.JenkinsCredentialsDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import static org.springframework.web.bind.annotation.RequestMethod.GET
import static org.springframework.web.bind.annotation.RequestMethod.POST
/**
 * Created by andrii.ievdokimov on 9/29/2015.
 */
@RestController
@RequestMapping(value = "/jenkins")
class UserController {

  @Autowired
  @Qualifier("loggedUser")
  User user

  @RequestMapping(value = "/instances", method = GET)
  Map<String, JenkinsCredentials> getJenkinsInstances() {
    user.getJenkinsCredentials()
  }

  @RequestMapping(value = "/instances", method = POST)
  void setJenkinsInstance(@RequestBody List<JenkinsCredentialsDTO> jCredDTO) {
    jCredDTO.each{
      user.saveJenkinsCredentials(it)
    }

  }

  @RequestMapping(value = "/hosts", method = POST)
  void setJenkinsState(@RequestBody def stateDTO) {
      stateDTO.each {it ->
          def credentials = new JenkinsCredentialsDTO()
          credentials.name = it['name']
          credentials.URL = it['address']
          credentials.userName = it['username']
          credentials.userPassword = it['password']
          user.saveJenkinsCredentials(credentials)
          it['jobs'].each {j ->
              user.saveJenkinsJob(credentials.name, j['name'])
          }
      }
  }

  @RequestMapping(value = "/hosts", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<HostsAndJobsDTO>  getJenkinsState() {
    List<HostsAndJobsDTO> allHostsAndJobs = new ArrayList<HostsAndJobsDTO>()
    def savedJenkinsInstances = user.getSavedJenkinsJobs()
    (savedJenkinsInstances.keySet() as List).each{jenkinsName ->
      HostsAndJobsDTO hostAndJobs = new HostsAndJobsDTO()
      def credentials = user.getJenkinsCredentials(jenkinsName)
      hostAndJobs.address = credentials.URL
      hostAndJobs.name = credentials.name
      hostAndJobs.username = credentials.userName
      hostAndJobs.password = credentials.userPassword

      hostAndJobs.jobs = []
      savedJenkinsInstances[jenkinsName].each{job->
        hostAndJobs.jobs.add(user.getJenkinsHelperByName(jenkinsName).getJobByName(job))
      }
      allHostsAndJobs.add(hostAndJobs)
    }
    allHostsAndJobs
  }
}
