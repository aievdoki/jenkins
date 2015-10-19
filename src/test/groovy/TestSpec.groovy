import com.avid.hydra.jenkins.client.JenkinsMonitorClient
import com.avid.hydra.jenkins.client.RestClientBuilder
import com.avid.hydra.jenkins.dto.JenkinsJobDTO
import spock.lang.Specification

/**
 * Created by andrii.ievdokimov on 9/28/2015.
 */
class TestSpec extends Specification {

  def "test jenkins client"() {
    when:
    def client = new RestClientBuilder("http://muc-srvci1.global.avidww.com:18080/", "aievdoki", "Belkevich1929").build()

    then:
    JenkinsJobDTO job = new JenkinsMonitorClient(client).getJob("core-master-api-local")
    job.buildable


  }
}
