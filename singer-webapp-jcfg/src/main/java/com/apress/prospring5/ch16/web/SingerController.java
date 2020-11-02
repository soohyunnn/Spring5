package com.apress.prospring5.ch16.web;

import java.util.List;
import java.util.Locale;

import com.apress.prospring5.ch16.util.Message;
import com.apress.prospring5.ch16.util.UrlUtil;
import com.apress.prospring5.ch16.entities.Singer;
import com.apress.prospring5.ch16.services.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;

@RequestMapping("/singers")
@Controller
public class SingerController {
    private final Logger logger = LoggerFactory.getLogger(SingerController.class);

    private SingerService singerService;
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        logger.info("Listing singers");

        List<Singer> singers = singerService.findAll();
        uiModel.addAttribute("singers", singers);

        logger.info("No. of singers: " + singers.size());

        return "singers/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, Model uiModel) {
        Singer singer = singerService.findById(id);
        uiModel.addAttribute("singer", singer);

        return "singers/show";
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
    public String update(@Valid Singer singer, BindingResult bindingResult, Model uiModel,
                         HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                         Locale locale) {
        logger.info("Updating singer");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message("error",
                    messageSource.getMessage("singer_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("singer", singer);
            return "singers/update";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success",
                messageSource.getMessage("singer_save_success", new Object[]{}, locale)));
        singerService.save(singer);
        return "redirect:/singers/" + UrlUtil.encodeUrlPathSegment(singer.getId().toString(),
                httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("singer", singerService.findById(id));
        return "singers/update";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid Singer singer, BindingResult bindingResult, Model uiModel,
            HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
            Locale locale, @RequestParam(value="file", required=false) Part file) {
        logger.info("Creating singer");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message("error",
                    messageSource.getMessage("singer_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("singer", singer);
            return "singers/create";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success",
                messageSource.getMessage("singer_save_success", new Object[]{}, locale)));

        logger.info("Singer id: " + singer.getId());
        singerService.save(singer);
        return "redirect:/singers/";
    }



    @RequestMapping(params="form", method = RequestMethod.GET)
    public String createForm(Model uiModel) {
    	Singer singer = new Singer();
    	uiModel.addAttribute("singer", singer);
    	return "singers/create";
    }
    

    @Autowired
    public void setSingerService(SingerService singerService) {
        this.singerService = singerService;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
