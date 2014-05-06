package ua.quiz.da.entity;

import ua.quiz.CategoryType;

import javax.persistence.*;
import java.util.Set;

/**
 * @author omar
 */
@Table(name = "\"question\"")
@Entity
public class QuestionEntity {
    private int id;
    private CategoryType categoryName;
    private String question;
    private AnswerEntity correctAnswer;
    private Set<AnswerEntity> answers;

	@Column(name = "id")
	@Id
	public int getId() {
		return id;
	}

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "category", nullable = false, length = 40)
    @Basic
    @Enumerated(EnumType.STRING)
    public CategoryType getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryType name) {
        this.categoryName = name;
    }

    @Column(name = "question", nullable = false, length = 255)
    @Basic
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @OneToOne
    public AnswerEntity getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(AnswerEntity correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @OneToMany(mappedBy = "answer")
    public Set<AnswerEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<AnswerEntity> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionEntity that = (QuestionEntity) o;

        if (id != that.id) return false;
        if (answers != null ? !answers.equals(that.answers) : that.answers != null) return false;
        if (categoryName != that.categoryName) return false;
        if (!correctAnswer.equals(that.correctAnswer)) return false;
        if (!question.equals(that.question)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + categoryName.hashCode();
        result = 31 * result + question.hashCode();
        result = 31 * result + correctAnswer.hashCode();
        result = 31 * result + (answers != null ? answers.hashCode() : 0);
        return result;
    }
}
