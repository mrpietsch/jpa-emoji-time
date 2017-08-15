package org.pspace.jpaconfigtest.unicode;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "text_entities")
public class TextEntity implements Serializable, EntityWithId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String text;

    public TextEntity()           {}

    public Long getId()              {return this.id;}

    public void setId(Long id)       {this.id = id; }

    public String getName()          {return this.name;}

    public void setName(String name) {this.name = name; }

    public String getText()          {return this.text;}

    public void setText(String text) {this.text = text; }

    @Override
    public String toString() {
        return "TextEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof TextEntity)) return false;
        final TextEntity other = (TextEntity) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name  = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$text  = this.getText();
        final Object other$text = other.getText();
        if (this$text == null ? other$text != null : !this$text.equals(other$text)) return false;
        return true;
    }

    public int hashCode() {
        final int    PRIME  = 59;
        int          result = 1;
        final Object $name  = this.getName();
        result = result * PRIME + ($name == null ? 0 : $name.hashCode());
        final Object $text = this.getText();
        result = result * PRIME + ($text == null ? 0 : $text.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {return other instanceof TextEntity;}
}
