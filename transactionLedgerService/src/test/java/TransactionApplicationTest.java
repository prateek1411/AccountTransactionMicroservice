import com.prateek.projects.microservices.transactionService.api.TransactionLedgerApi;
import com.prateek.projects.microservices.transactionService.model.TransactionLedger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.prateek.projects.microservices.transactionService.TransactionApplication.class)
public class TransactionApplicationTest {

    @Autowired
    private TransactionLedgerApi transactionLedgerApi;

    @Autowired
    private TransactionLedger transactionLedger;

    @Test
    public void listTransactionTest() throws Exception {
        transactionLedger.setAccount(1L);
        transactionLedger.setTransactionType("D");
        transactionLedger.setAmount(200L);
        transactionLedgerApi.listTransactions(1L);
    }

    @Test
    public void getBalance() throws Exception {
        transactionLedger.setAccount(1L);
        transactionLedger.setTransactionType("D");
        transactionLedger.setAmount(200L);
        transactionLedgerApi.create(transactionLedger);

        transactionLedger.setAccount(1L);
        transactionLedger.setTransactionType("D");
        transactionLedger.setAmount(200L);
        transactionLedgerApi.create(transactionLedger);

        transactionLedger.setAccount(1L);
        transactionLedger.setTransactionType("D");
        transactionLedger.setAmount(200L);
        transactionLedgerApi.create(transactionLedger);

        transactionLedger.setAccount(1L);
        transactionLedger.setTransactionType("D");
        transactionLedger.setAmount(200L);
        transactionLedgerApi.create(transactionLedger);

        transactionLedgerApi.getBalance(1L);
    }
}
