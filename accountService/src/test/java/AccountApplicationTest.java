import com.prateek.projects.microservices.accountService.api.AccountApi;
import com.prateek.projects.microservices.accountService.model.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.prateek.projects.microservices.accountService.AccountApplication.class)
public class AccountApplicationTest {

    @Autowired
    private AccountApi accountApi;

    @Autowired
    private Account account;

    @Test
    public void contextLoads() throws Exception {
        account.setCustomer(1L);
        account.setAccountType("Current");
        account.setCurrentAccountBalance(0L);
        accountApi.create(account);
    }
}
