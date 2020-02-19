package Controller;

import Repository.ActivityRepository;
import com.oscarwildcircus.Entity.Activity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/activity")
public class ActivityController {
    private final ActivityRepository activityRepository;

    public ActivityController(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @GetMapping("/create")
    private String createActivity(Model model) throws Exception{
        model.addAttribute("page", "activityList");
        model.addAttribute("pathMethod", "activity/create");
        model.addAttribute("newActivity",new Activity());
        return "pages/activity/view";
    }
}
