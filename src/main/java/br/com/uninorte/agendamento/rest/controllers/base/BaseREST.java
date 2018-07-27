package br.com.uninorte.agendamento.rest.controllers.base;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.querydsl.core.types.Predicate;

import br.com.uninorte.agendamento.rest.services.base.IBaseBO;
import lombok.AccessLevel;
import lombok.Getter;
import springfox.documentation.annotations.ApiIgnore;

public abstract class BaseREST<T extends IBaseBO<?, TDTO, Long>, TDTO> {
	@Getter(AccessLevel.PROTECTED)
	private 	final		T			service;

	public BaseREST(T service) {
		this.service 	= service;
	}

	@RequestMapping(method= RequestMethod.GET, path = "/{id}")
	public ResponseEntity<?> findOne(@PathVariable("id") UUID id, @ApiIgnore @AuthenticationPrincipal UserDetails details) {
		return ResponseEntity.ok(service.findOne(id, details));
	}

	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity<?> findAll(@ApiIgnore @AuthenticationPrincipal UserDetails details) {
		return ResponseEntity.ok(service.findAll(details));
	}

	@RequestMapping(method= RequestMethod.GET, path = "/page")
	public ResponseEntity<?> findAll(Pageable pageable, @ApiIgnore @AuthenticationPrincipal UserDetails details) {
		return ResponseEntity.ok(service.findAll(pageable, details));
	}

	@RequestMapping(method= RequestMethod.POST)
	public ResponseEntity<TDTO> save(@RequestBody @Valid TDTO dto, @ApiIgnore @AuthenticationPrincipal UserDetails account) {
		return ResponseEntity.ok(service.save(dto, account));
	}

	@RequestMapping(method= RequestMethod.PUT)
	public ResponseEntity<TDTO> update(@RequestBody @Valid TDTO dto, @ApiIgnore @AuthenticationPrincipal UserDetails account) {
		return ResponseEntity.ok(service.update(dto, account));
	}

	@RequestMapping(method=RequestMethod.PUT, path = "/enable")
	@ResponseBody
	public ResponseEntity<?> enable(@RequestParam UUID id, @RequestParam boolean enable) {
		return ResponseEntity.ok(getService().setEnabled(id, enable));
	}
	
}
