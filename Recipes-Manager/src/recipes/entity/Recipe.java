package recipes.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import recipes.validation.OneItemList;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "recipe")
@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonIgnore
    int id;

    @Column(name = "name")
    @NotBlank(message = "Name must not be empty.")
    String name;

    @Column(name = "category")
    @NotBlank(message = "Category must not be empty.")
    String category;

    @Column(name = "date")
    @LastModifiedDate
    @JsonFormat(pattern = "EEE, d MMM yyyy'г.' HH:mm")
    LocalDateTime date;

    @Column(name = "description")
    @NotBlank(message = "Description must not be empty.")
    String description;

    @Column(name = "ingredients")
    @OneItemList
    @ElementCollection
    List<String> ingredients;

    @Column(name = "directions")
    @OneItemList
    @ElementCollection
    List<String> directions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", nullable = false)
    @JsonIgnore
    User user;
}