package edu.tcu.cs.peerevalutationtool.section;

import edu.tcu.cs.peerevalutationtool.section.converter.SectionToSectionDtoConverter;
import edu.tcu.cs.peerevalutationtool.section.dto.SectionDto;
import edu.tcu.cs.peerevalutationtool.system.Result;
import edu.tcu.cs.peerevalutationtool.system.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class SectionController {

    private final SectionService sectionService;

    private final SectionToSectionDtoConverter sectionToSectionDtoConverter;

    public SectionController(SectionService sectionService, SectionToSectionDtoConverter sectionToSectionDtoConverter) {
        this.sectionService = sectionService;
        this.sectionToSectionDtoConverter = sectionToSectionDtoConverter;
    }

    // Find a section with a certain ID
    @GetMapping("/api/v1/sections/{sectionId}")
    public Result findSectionById(@PathVariable String sectionId){
        Section foundSection = this.sectionService.findById(sectionId);
        SectionDto sectionDto = this.sectionToSectionDtoConverter.convert(foundSection);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", sectionDto);
    }

    // May not need
    @GetMapping("/api/v1/sections/yr/{sectionYear}")
    public Result findSectionByYear(@PathVariable String sectionYear){
        Section foundSection = this.sectionService.findByYear(sectionYear);
        SectionDto sectionDto = this.sectionToSectionDtoConverter.convert(foundSection);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", sectionDto);
    }

    // May not need
    @GetMapping("/api/v1/sections/secyr/{sectionId}+{sectionYear}")
    public Result findSectionByIdAndYear(@PathVariable String sectionId, @PathVariable String sectionYear){
        Section foundSection = this.sectionService.findByIdAndYear(sectionId, sectionYear);
        SectionDto sectionDto = this.sectionToSectionDtoConverter.convert(foundSection);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", sectionDto);
    }

    // Return all sections
    @GetMapping("/api/v1/sections")
    public Result findAllSections(){
        List<Section> foundSections = this.sectionService.findAll();
        // Convert foundSections to a list of sectionDtos
        List<SectionDto> sectionDtos = foundSections.stream()
                .map(foundSection -> this.sectionToSectionDtoConverter.convert(foundSection))
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find All Success", sectionDtos);
    }

    // Return all sections with a given year
    @GetMapping("/api/v1/sections/allbyyear/{sectionName}")
    public Result findAllByYear(@PathVariable String sectionName){
        List<Section> foundSections = this.sectionService.findAllByYear(sectionName);
        // Convert foundSections to a list of sectionDtos
        List<SectionDto> sectionDtos = foundSections.stream()
                .map(foundSection -> this.sectionToSectionDtoConverter.convert(foundSection))
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find All By Year Success", sectionDtos);
    }

    // Add a section
    @PostMapping()
    public Result addSection(){
        return null;
    }

}
