package ua.com.foxminded.quickpoll.v2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.foxminded.quickpoll.domain.Vote;
import ua.com.foxminded.quickpoll.dto.OptionCount;
import ua.com.foxminded.quickpoll.dto.VoteResult;
import ua.com.foxminded.quickpoll.repository.VoteRepository;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@RestController("computeResultControllerV2")
@RequestMapping("/v2/computeresult")
@Tag(name = "computeresult", description = "Compute Results API")
public class ComputeResultController {

    @Inject
    private VoteRepository voteRepository;

    @RequestMapping(method = RequestMethod.GET)
    @Operation(summary = "Computes the results of a given Poll", responses = {
            @ApiResponse(responseCode = "400", description = "Compute Results",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VoteResult.class))),
            @ApiResponse(responseCode = "400", description = "Polls not found")})
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);
        int totalVotes = 0;
        Map<Long, OptionCount> tempMap = new HashMap<Long, OptionCount>();
        for (Vote v : allVotes) {
            totalVotes++;
            OptionCount optionCount = tempMap.get(v.getOption().getId());
            if (optionCount == null) {
                optionCount = new OptionCount();
                optionCount.setOptionId(v.getOption().getId());
                tempMap.put(v.getOption().getId(), optionCount);
            }
            optionCount.setCount(optionCount.getCount() + 1);
        }
        voteResult.setTotalVotes(totalVotes);
        voteResult.setResults(tempMap.values());
        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }
}