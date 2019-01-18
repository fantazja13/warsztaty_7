package pl.coderslab.warsztaty_7.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.coderslab.warsztaty_7.model.Budget;
import pl.coderslab.warsztaty_7.model.Receipt;
import pl.coderslab.warsztaty_7.model.User;
import pl.coderslab.warsztaty_7.repository.ReceiptRepository;
import pl.coderslab.warsztaty_7.service.ReceiptService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Primary
public class ReceiptServiceJpaImpl implements ReceiptService {

    private ReceiptRepository receiptRepository;

    @Autowired
    public ReceiptServiceJpaImpl(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }


    @Override
    public List<Receipt> findAllOrderedByDate() {
        return receiptRepository.findAllByOrderByDateOfPaymentDesc();
    }

    @Override
    public Receipt findById(Long id) {
        return receiptRepository.findOne(id);
    }

    @Override
    public Receipt create(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    @Override
    public Receipt edit(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    @Override
    public void deleteById(Long id) {
        receiptRepository.delete(id);
    }

    @Override
    public Receipt findByExpenseId(Long id) {
        return null;
    }

    @Override
    public List<Receipt> findLast5ReceiptsForBudget(Budget budget) {
        Collection<Long> ids = new ArrayList<>();
        for (User user: budget.getUsers()) {
            ids.add(user.getId());
        }
        return receiptRepository.findTop5ReceiptsByCreatedByUserIdInOrderByDateOfPaymentDesc(ids);
    }
}