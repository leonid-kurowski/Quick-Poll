package ua.com.foxminded.quickpoll.v2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.com.foxminded.quickpoll.domain.Poll;
import ua.com.foxminded.quickpoll.dto.error.ErrorDetail;
import ua.com.foxminded.quickpoll.exception.ResourceNotFoundException;
import ua.com.foxminded.quickpoll.repository.PollRepository;
import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;

@RestController("pollControllerV2")
@RequestMapping("/v2/")
@Tag(name = "polls", description = "Poll API")
public class PollController {

    @Inject
    private PollRepository pollRepository;

    @PostMapping("/polls")
    @Operation(summary = "Creates a new Poll", description = "The newly created poll Id will be sent in the location response header",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Poll Created Successfully",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "500", description = "Error creating Poll",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDetail.class)))})
    public ResponseEntity<Void> createPoll(@Valid @RequestBody Poll poll) {
        poll = pollRepository.save(poll);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(poll.getId()).toUri();
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/polls")
    @Operation(summary = "Retrieves given Poll", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Poll.class))),
            @ApiResponse(responseCode = "404", description = "Unable to find Poll",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class)))})
    public ResponseEntity<Page<Poll>> getAllPolls(@Parameter(hidden = true) Pageable pageable) {
        Page<Poll> allPolls = pollRepository.findAll(pageable);
        return new ResponseEntity<>(allPolls, HttpStatus.OK);
    }

    @GetMapping("/polls/{pollId}")
    @Operation(summary = "Retrieves all the polls", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Poll.class))))})
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        verifyPoll(pollId);
        Poll p = null;
        if (pollRepository.findById(pollId).isPresent()) {
            p = pollRepository.findById(pollId).get();
        }
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PutMapping("/polls/{pollId}")
    @Operation(summary = "Updates given Poll", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Unable to find Poll",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class)))})
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
        verifyPoll(pollId);
        Poll p = pollRepository.save(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/polls/{pollId}")
    @Operation(summary = "Deletes given Poll", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Unable to find Poll",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class)))})
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        verifyPoll(pollId);
        Poll p = null;
        if (pollRepository.findById(pollId).isPresent()) {
            p = pollRepository.findById(pollId).get();
        }
        pollRepository.delete(p);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
        if (pollRepository.findById(pollId).isEmpty()) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }
    }
}
