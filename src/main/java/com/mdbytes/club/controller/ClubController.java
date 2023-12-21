package com.mdbytes.club.controller;

import com.mdbytes.club.dto.ClubDto;
import com.mdbytes.club.model.UserEntity;
import com.mdbytes.club.security.SecurityUtil;
import com.mdbytes.club.service.ClubService;
import com.mdbytes.club.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {

    private ClubService clubService;

    private UserService userService;

    @Autowired
    public ClubController(ClubService clubService, UserService userService) {
        this.clubService = clubService;
        this.userService = userService;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model) {
        List<ClubDto> clubs = clubService.findAllClubs();
        model.addAttribute("clubs", clubs);

        UserEntity user = getCurrentUser();
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "clubs/clubs-list";
    }

    @GetMapping("/clubs/new")
    public String createClubForm(Model model) {
        ClubDto clubDto = new ClubDto();
        model.addAttribute("club", clubDto);
        return "clubs/clubs-create";
    }

    @PostMapping("/clubs/new")
    public String createNewClub(@Valid @ModelAttribute("club") ClubDto clubDto, BindingResult result) {
        if (result.hasErrors()) {
            return "clubs/clubs-create";
        }

        clubService.save(clubDto);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{id}/edit")
    public String editClubForm(@PathVariable("id") Integer id, Model model) {
        ClubDto clubDto = clubService.findById(id);
        model.addAttribute("club", clubDto);
        return "clubs/clubs-edit";
    }

    @PostMapping("/clubs/{id}/edit")
    public String editClub(
        @PathVariable("id") Integer id,
        @Valid @ModelAttribute("club") ClubDto clubDto,
        BindingResult result,
        Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("club", clubService.findById(id));
            return "clubs/clubs-edit";
        }

        clubDto.setId(id);
        clubService.updateClub(clubDto);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{id}")
    public String clubDetail(@PathVariable("id") Integer id, Model model) {
        ClubDto clubDto = clubService.findById(id);
        model.addAttribute("club", clubDto);
        UserEntity user = getCurrentUser();
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "clubs/clubs-detail";
    }

    @GetMapping("/clubs/{id}/delete")
    public String deleteClub(@PathVariable("id") Integer id) {
        clubService.delete(id);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/search")
    public String searchClub(@RequestParam(value = "query") String query, Model model) {
        List<ClubDto> clubs = clubService.searchClubs(query);
        model.addAttribute("clubs", clubs);
        UserEntity user = getCurrentUser();
        model.addAttribute("user", user);
        return "clubs/clubs-list";
    }

    private UserEntity getCurrentUser() {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userService.findByUsername(username);
        return user;
    }
}
