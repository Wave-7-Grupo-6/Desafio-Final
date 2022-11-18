package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.DiscountCouponDTO;
import br.com.dh.meli.desafiofinal.repository.DiscountCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscountCouponService implements IDiscountCoupon{
    private final DiscountCouponRepository repository;

    @Override
    public DiscountCouponDTO save(DiscountCouponDTO discountCouponDTO) {
        return new DiscountCouponDTO(repository.save(discountCouponDTO.toDiscountCoupon()));
    }

    @Override
    public DiscountCouponDTO update(DiscountCouponDTO discountCouponDTO, Long id) {
        findById(id);
        discountCouponDTO.setId(id);
        return save(discountCouponDTO);
    }

    @Override
    public void delete(Long id) {
        try{
            findById(id);
            repository.deleteById(id);
        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("You can't delete a discount coupon that has products associated!");
        }
    }

    @Override
    public List<DiscountCouponDTO> findAll() {
        return repository.findAll().stream().map(DiscountCouponDTO::new).collect(Collectors.toList());
    }

    @Override
    public DiscountCouponDTO findById(Long id) {
        return repository.findById(id).map(DiscountCouponDTO::new).orElseThrow(() -> new NoSuchElementException("Discount Coupon not found"));
    }

    @Override
    public List<DiscountCouponDTO> findByDiscount(Double discount) {
        return repository.findByDiscount(discount).stream().map(DiscountCouponDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<DiscountCouponDTO> findByCategoryId(Long categoryId) {
        return repository.findByCategoryId(categoryId).stream().map(DiscountCouponDTO::new).collect(Collectors.toList());
    }
}
