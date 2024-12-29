package CyLife.Websockets.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import CyLife.Clubs.ClubRepository;
import CyLife.Clubs.Club;
import CyLife.Websockets.chat.Message;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ClubRepository clubRepository;
    @GetMapping("/active")
    public List<Club> getActiveChats() {
        return clubRepository.findAll();
    }
    @GetMapping("/history/{clubId}")
    public List<Message> getChatHistory(@PathVariable int clubId) {
        return messageRepository.findByClubId(clubId);
    }
}
