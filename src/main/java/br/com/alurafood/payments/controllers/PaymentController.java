package br.com.alurafood.payments.controllers;import br.com.alurafood.payments.dto.PaymentDTO;import br.com.alurafood.payments.services.PaymentService;import jakarta.validation.Valid;import jakarta.validation.constraints.NotNull;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Page;import org.springframework.data.domain.Pageable;import org.springframework.data.web.PageableDefault;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.*;import org.springframework.web.util.UriComponentsBuilder;import java.net.URI;@RestController@RequestMapping("/payments")public class PaymentController {    @Autowired    private PaymentService service;    @GetMapping    public Page<PaymentDTO> allPayments(@PageableDefault(size = 10) Pageable pagination) {        return service.getAllPayments(pagination);    }    @GetMapping("/{id}")    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable @NotNull Long id) {        PaymentDTO paymentDTO = service.getPaymentById(id);        return ResponseEntity.ok(paymentDTO);    }    @PostMapping    public ResponseEntity<PaymentDTO> createPayment(@RequestBody @Valid PaymentDTO paymentDTO, UriComponentsBuilder uriComponentsBuilder) {        PaymentDTO payment = service.createPayment(paymentDTO);        URI address = uriComponentsBuilder.path("/payments/{id}").buildAndExpand(payment.getId()).toUri();        return ResponseEntity.created(address).body(payment);    }    @PutMapping("/{id}")    public ResponseEntity<PaymentDTO> updatePaymentById(@PathVariable @NotNull Long id, @RequestBody @Valid PaymentDTO paymentDTO) {        PaymentDTO payment = service.updatePayment(id, paymentDTO);        return ResponseEntity.ok(payment);    }    @DeleteMapping("/{id}")    public ResponseEntity<PaymentDTO> deletePaymentById(@PathVariable @NotNull Long id) {        service.deletePayment(id);        return ResponseEntity.noContent().build();    }}