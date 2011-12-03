package models;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Request extends Model
{
    @Required
    public String title;

    @Required
    @Lob
    public String description;

    @Required
    @OneToMany(mappedBy="request", cascade=CascadeType.ALL)
    public List<Tag> tags;

    public String getTags() {
	ListIterator tagIterator = tags.listIterator();
	String tidyTagList = "";
	if (tagIterator.hasNext()) {
	    tidyTagList += tagIterator.next();
	} else {
	    return tidyTagList;
	}
	while (tagIterator.hasNext()) {
	    tidyTagList += ", " + tagIterator.next();
	}
	return tidyTagList;
    }
    
    @Required
    @Temporal(TemporalType.TIMESTAMP)
    public Date endDate;

    @ManyToOne
    public User user;

    public Boolean isFinalized;

    public Request(User user) {
	this.user = user;
    }

    public Request() {
	
    }
}
