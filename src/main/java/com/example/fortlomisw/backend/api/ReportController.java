package com.example.fortlomisw.backend.api;




import com.example.fortlomisw.backend.domain.model.entity.Report;
import com.example.fortlomisw.backend.domain.service.ReportService;
import com.example.fortlomisw.backend.mapping.ReportMapper;
import com.example.fortlomisw.backend.resource.Report.CreateReportResource;
import com.example.fortlomisw.backend.resource.Report.ReportResource;
import com.example.fortlomisw.backend.resource.Report.UpdateReportResource;


import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;







@RestController
@RequestMapping("/api/v1")
public class ReportController {







    @Autowired
    private ReportService reportservice;

    @Autowired
    private ReportMapper mapper;

    @Autowired
    private ModelMapper mapping;
    @GetMapping("/reports")
    public Page<ReportResource> getAllRates(Pageable pageable) {
        return mapper.modelListToPage(reportservice.getAll(), pageable);
    }
    @GetMapping("/reports/{reportId}")
    public ReportResource getRateById(@PathVariable("reportId") Long followId) {
        return mapper.toResource(reportservice.getById(followId));
    }
    @PostMapping("/usersmains/{UserMainId}/usersreports/{UserReportedId}/reports")
    public ReportResource createRate(@PathVariable Long UserMainId, @PathVariable Long UserReportedId, @RequestBody CreateReportResource request) {
        Report comment = mapping.map(request, Report.class);
        return mapping.map(reportservice.create(UserMainId, UserReportedId, comment), ReportResource.class);
    }
    @GetMapping("/usersmains/{UserMainId}/reports")
    public Page<ReportResource> getAllRatesByFanaticId(@PathVariable Long UserMainId,Pageable pageable) {
        return mapper.modelListToPage(reportservice.findByUserMainId(UserMainId), pageable);
    }
    @GetMapping("/usersreports/{UserReportedId}/reports")
    public Page<ReportResource> getAllRateByArtistId(@PathVariable Long UserReportedId,Pageable pageable) {
        return mapper.modelListToPage(reportservice.findByUserReportedId(UserReportedId), pageable);
    }
    @DeleteMapping("/reports/{reportId}")
    public ResponseEntity<?> deleteFollow(@PathVariable Long rateId) {
        return reportservice.delete(rateId);
    }


    @PutMapping("/reports/{reportId}")
    public ReportResource updateRate(@PathVariable Long reportId, @RequestBody UpdateReportResource request) {
        return mapper.toResource(reportservice.update(reportId, mapper.toModel(request)));
    }






}
