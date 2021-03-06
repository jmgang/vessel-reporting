package com.karagathon;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.karagathon.model.Notification;
import com.karagathon.service.NotificationService;

@Controller
public class NotificationController {
	
	
	@Autowired
	NotificationService notificationService;
	
	@PostMapping("/fetch-report-records")
    public ResponseEntity<?> getNotificationsViaAjax(@Valid @RequestParam("view") String search) {
    	

//        AjaxResponseBody result = new AjaxResponseBody();
		
		String result;
		List<Notification> notifications = notificationService.getNotifications( 5 );
		
		System.out.println(notifications);
		
		final StringBuffer output = new StringBuffer();
		if( Objects.isNull(search) || search.trim().isEmpty() ) {	
			if( !notifications.isEmpty() ) {
				notifications.forEach(notification -> {
					output.append( "<li><a class=\"stretched-link\" href='" )
							.append( notification.getUrl() ) //  . $row->subject .  . $row->text . 
							.append("'><strong>")
							.append( notification.getSubject() )
							.append("</strong><br><small><em>")
							.append( notification.getText() )
							.append( "</em></small></a></li><liclass='divider'></li>" );
				});
				
			}
			
			else {
				output.append("<li><a href=\"#\" class=\"text-bold text-italic\">No Notifications Found</a></li>");
			}
		}else {
			
		}
		
		System.out.println( "output: " + output );
	

        return ResponseEntity.ok( output.toString() );

    }
}
