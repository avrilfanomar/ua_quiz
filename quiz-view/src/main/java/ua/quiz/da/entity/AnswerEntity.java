package ua.quiz.da.entity;

import ua.quiz.CategoryType;

import javax.persistence.*;

/**
 * @author omar
 */
@Table(name = "\"answer\"")
@Entity
public class AnswerEntity {
	private int id;
	private int questionId;
	private String answer;

    @Column(name = "id")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "question_id", nullable = false)
    @Basic
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Column(name = "answer", nullable = false, length = 100)
    @Basic
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerEntity that = (AnswerEntity) o;

        if (id != that.id) return false;
        if (questionId != that.questionId) return false;
        if (!answer.equals(that.answer)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + questionId;
        result = 31 * result + answer.hashCode();
        return result;
    }
}
