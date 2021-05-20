package ua.com.foxminded.quickpoll.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ua.com.foxminded.quickpoll.domain.Option;
import ua.com.foxminded.quickpoll.domain.Poll;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

public class QuickPollClientV2 {

    private static final String QUICK_POLL_URI_2 = "http://localhost:8080/v2/polls";
    private final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        QuickPollClientV2 client = new QuickPollClientV2();
        Poll poll = client.getPollById(1L);
        System.out.println(poll);
        RestResponsePage<Poll> allPolls = client.getAllPolls(2, 3);
        System.out.println(allPolls);
        Poll newPoll = new Poll();
        newPoll.setQuestion("What is your favourate color 2?");
        Set<Option> options = new HashSet<>();
        newPoll.setOptions(options);
        Option option1 = new Option();
        option1.setValue("Red");
        options.add(option1);
        Option option2 = new Option();
        option2.setValue("Blue");
        options.add(option2);
        URI pollLocation = client.createPoll(newPoll);
        System.out.println("Newly Created Poll Location " + pollLocation);
        Poll pollForId6 = client.getPollById(6L);
        Option newOption = new Option();
        newOption.setValue("The Incredibles 2");
        pollForId6.getOptions().add(newOption);
        client.updatePoll(pollForId6);
        pollForId6 = client.getPollById(6L);
        System.out.println("Updated poll has " + pollForId6.getOptions().size() + " options");
        client.deletePoll(9L);
    }

    public RestResponsePage<Poll> getAllPolls(int page, int size) {
        ParameterizedTypeReference<RestResponsePage<Poll>> responseType = new ParameterizedTypeReference<RestResponsePage<Poll>>() {
        };
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(QUICK_POLL_URI_2)
                .queryParam("page", page)
                .queryParam("size", size);

        ResponseEntity<RestResponsePage<Poll>> responseEntity = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, null, responseType);
        RestResponsePage<Poll> allPolls = responseEntity.getBody();
        return allPolls;
    }

    public Poll getPollById(Long pollId) {
        return restTemplate.getForObject(QUICK_POLL_URI_2 + "/{pollId}", Poll.class, pollId);
    }

    public URI createPoll(Poll poll) {
        return restTemplate.postForLocation(QUICK_POLL_URI_2, poll);
    }

    public void updatePoll(Poll poll) {
        restTemplate.put(QUICK_POLL_URI_2 + "/{pollId}", poll, poll.getId());
    }

    public void deletePoll(Long pollId) {
        restTemplate.delete(QUICK_POLL_URI_2 + "/{pollId}", pollId);
    }
}
