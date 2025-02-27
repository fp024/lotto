package org.fp024.lotto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/lotto")
@Controller
public class LottoController {
  @GetMapping("/list")
  public void list() {}
}
