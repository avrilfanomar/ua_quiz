package ua.quiz.da.entity;

import javax.persistence.*;

/**
 * @author omar
 */
@Table(name = "\"answer\"")
@Entity
public class AnswerEntity {
    private int id;
    private QuestionEntity question;
    private String answer;

    @Column(name = "id")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
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
        if (!answer.equals(that.answer)) return false;
        if (!question.equals(that.question)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + question.hashCode();
        result = 31 * result + answer.hashCode();
        return result;
    }
}
