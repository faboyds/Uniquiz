package phoenix.uniquizandroid.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import phoenix.uniquizandroid.R;
import phoenix.uniquizandroid.dto.AnswerDTO;
import phoenix.uniquizandroid.dto.QuestionDTO;
import phoenix.uniquizandroid.dto.QuizDTO;
import phoenix.uniquizandroid.dto.SolutionDTO;
import phoenix.uniquizandroid.restclient.AppSession;
import phoenix.uniquizandroid.restclient.RestProperties;

public class QuizActivity extends AppCompatActivity {

    private QuizDTO quiz;
    private boolean isRevision;
    private List<QuestionDTO> questions;
    private int[] quizAnswers;
    private int correctAns;
    private int wrongAns;
    private int questionCount;
    private int questionsListPos;
    private Button answer1Button;
    private Button answer2Button;
    private Button answer3Button;
    private Button answer4Button;
    private ImageButton nextQuestion;
    private ImageButton previousQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        quiz = (QuizDTO) getIntent().getSerializableExtra("quiz");

        questions = quiz.getQuestions();
        questionCount = 1;
        questionsListPos = 0;
        quizAnswers = new int[questions.size()];

        answer1Button = (Button) findViewById(R.id.answer1_button);
        answer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizAnswers[questionsListPos] = 1;
                resetButtonsColor();
                answer1Button.setBackgroundColor(getResources().getColor(R.color.colorAnswer, null));
            }
        });

        answer2Button = (Button) findViewById(R.id.answer2_button);
        answer2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizAnswers[questionsListPos] = 2;
                resetButtonsColor();
                answer2Button.setBackgroundColor(getResources().getColor(R.color.colorAnswer, null));

            }
        });

        answer3Button = (Button) findViewById(R.id.answer3_button);
        answer3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizAnswers[questionsListPos] = 3;
                resetButtonsColor();
                answer3Button.setBackgroundColor(getResources().getColor(R.color.colorAnswer, null));;
            }
        });

        answer4Button = (Button) findViewById(R.id.answer4_button);
        answer4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizAnswers[questionsListPos] = 4;
                resetButtonsColor();
                answer4Button.setBackgroundColor(getResources().getColor(R.color.colorAnswer, null));
            }
        });

        nextQuestion = (ImageButton) findViewById(R.id.next_question_button);
        previousQuestion = (ImageButton) findViewById(R.id.previous_question_button);

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionsListPos < questions.size() - 1) {
                    nextQuestion();
                    refreshNextButton();
                }


            }

        });

        previousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionsListPos > 0) {
                    previousQuestion();
                    refreshPreviousButton();
                }
            }
        });

        Button finalizeQuiz = (Button) findViewById(R.id.finish_button);
        finalizeQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishQuiz();
            }
        });
        previousQuestion.setClickable(false);
        previousQuestion.setVisibility(View.INVISIBLE );
        refreshQuestion();
    }
    private void refreshPreviousButton(){
        if(questionsListPos < questions.size() - 1) {
            if (questionsListPos == 0) {
                previousQuestion.setClickable(false);
                previousQuestion.setVisibility(View.INVISIBLE);

                nextQuestion.setClickable(true);
                nextQuestion.setVisibility(View.VISIBLE);
            }
            if (questionsListPos == questions.size() - 2) {
                nextQuestion.setClickable(true);
                nextQuestion.setVisibility(View.VISIBLE);
            }
        }
    }

    private void refreshNextButton(){
        if(questionsListPos > 0) {
            if (questionsListPos == questions.size() - 1) {
                nextQuestion.setClickable(false);
                nextQuestion.setVisibility(View.INVISIBLE);
                previousQuestion.setClickable(true);
                previousQuestion.setVisibility(View.VISIBLE);
            }
            if (questionsListPos == 1) {
                previousQuestion.setClickable(true);
                previousQuestion.setVisibility(View.VISIBLE);
            }
        }
    }
    private void refreshQuestion(){
        TextView questionCountView = (TextView) findViewById(R.id.question_count_view);
        questionCountView.setText(String.valueOf(questionCount)+ " OF " +String.valueOf(questions.size()));
        TextView questionView = (TextView) findViewById(R.id.question_view);
        questionView.setText(questions.get(questionsListPos).getQuestion());
        refreshButtons();
    }

    private void refreshButtons(){
        List<AnswerDTO> answers = questions.get(questionsListPos).getAnswers();
        int numberOfAnswers = answers.size();
        resetButtonsColor();
        answer1Button.setText(answers.get(0).getAnswer());
        answer2Button.setText(answers.get(1).getAnswer());
        if(numberOfAnswers >= 3){
            if(!isRevision) answer3Button.setClickable(true);
            answer3Button.setVisibility(View.VISIBLE);
            answer3Button.setText(answers.get(2).getAnswer());
        }else{
            answer3Button.setClickable(false);
            answer3Button.setVisibility(View.INVISIBLE);
        }
        if(numberOfAnswers == 4){
            if(!isRevision) answer4Button.setClickable(true);
            answer4Button.setVisibility(View.VISIBLE);
            answer4Button.setText(answers.get(3).getAnswer());
        }else{
            answer4Button.setClickable(false);
            answer4Button.setVisibility(View.INVISIBLE);
        }

        if(!isRevision){
            switch(quizAnswers[questionsListPos]){
                case 1:
                    answer1Button.callOnClick();
                    break;
                case 2:
                    answer2Button.callOnClick();
                    break;
                case 3:
                    answer3Button.callOnClick();
                    break;
                case 4:
                    answer4Button.callOnClick();
                    break;
            }
        }else{
            int answer = quizAnswers[questionsListPos];
            if(answer>0){
                if(!questions.get(questionsListPos).getAnswers().get(answer-1).isRightAnswer()){
                    switch(answer){
                        case 1:
                            resetButtonsColor();
                            answer1Button.setBackgroundColor(getResources().getColor(R.color.colorWrongAnswer, null));
                            break;
                        case 2:
                            resetButtonsColor();
                            answer2Button.setBackgroundColor(getResources().getColor(R.color.colorWrongAnswer, null));
                            break;
                        case 3:
                            resetButtonsColor();
                            answer3Button.setBackgroundColor(getResources().getColor(R.color.colorWrongAnswer, null));

                            break;
                        case 4:
                            resetButtonsColor();
                            answer4Button.setBackgroundColor(getResources().getColor(R.color.colorWrongAnswer, null));

                            break;
                    }
                }
            }

            int colorRightAnswer = getResources().getColor(R.color.colorRightAnswer, null);
            if(answer == 0){
                colorRightAnswer = getResources().getColor(R.color.colorUnanswered, null);
            }

            int rightAnswer = 1;
            for(AnswerDTO correctAns : questions.get(questionsListPos).getAnswers()){
                if(correctAns.isRightAnswer()){
                    break;
                }else{
                    rightAnswer++;
                }
            }

            switch(rightAnswer){
                case 1:
                    answer1Button.setBackgroundColor(colorRightAnswer);
                    break;
                case 2:
                    answer2Button.setBackgroundColor(colorRightAnswer);
                    break;
                case 3:
                    answer3Button.setBackgroundColor(colorRightAnswer);
                    break;
                case 4:
                    answer4Button.setBackgroundColor(colorRightAnswer);
                    break;
            }

        }


    }

    private void nextQuestion(){
        questionCount++;
        questionsListPos++;
        refreshQuestion();
    }

    private void previousQuestion(){
        questionCount--;
        questionsListPos--;
        refreshQuestion();

    }

    private void finishQuiz(){
        if(!isRevision){
            for (int i = 0; i < quizAnswers.length; i++) {
                int answer = quizAnswers[i];
                if(answer>0){
                    answer--;
                    if(questions.get(i).getAnswers().get(answer).isRightAnswer()){
                        correctAns++;
                    }else{
                        wrongAns++;
                    }
                }else{
                    wrongAns++;
                }

            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You have answered correctly to " + correctAns + " questions, with " + wrongAns + " answers wrong.")
                    .setPositiveButton("Review", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            isRevision = true;
                            reviewQuiz();
                        }
                    })
                    .setNegativeButton("Finish", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            new HttpRequestTask().execute();
                            onBackPressed();
                        }
                    });
            builder.create().show();
        }else{
            new HttpRequestTask().execute();
            onBackPressed();
        }
    }

    private void resetButtonsColor(){
        answer1Button.setBackgroundColor(Color.WHITE);
        answer2Button.setBackgroundColor(Color.WHITE);
        answer3Button.setBackgroundColor(Color.WHITE);
        answer4Button.setBackgroundColor(Color.WHITE);
    }

    private void reviewQuiz(){
        answer1Button.setClickable(false);
        answer2Button.setClickable(false);
        answer3Button.setClickable(false);
        answer4Button.setClickable(false);
        questionCount = 1;
        questionsListPos = 0;
        refreshNextButton();
        refreshPreviousButton();
        refreshQuestion();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, SolutionDTO> {

        @Override
        protected SolutionDTO doInBackground(Void... params) {
            RestProperties webProperties = new RestProperties(QuizActivity.this);
            final UriComponents uri = UriComponentsBuilder.newInstance().scheme(webProperties.getScheme())
                    .host(webProperties.getHost())
                    .port(webProperties.getPort())
                    .path(webProperties.getAppBaseUri()
                            +"/" + webProperties.getSolutionsUri())
                    .build();

            HttpHeaders headers = new HttpHeaders();

            SolutionDTO body = new SolutionDTO(quiz.getPk(),
                    AppSession.loggedUser.getEmail(),
                    Byte.valueOf(String.valueOf(correctAns)),
                    Byte.valueOf(String.valueOf(wrongAns)));

            HttpEntity<SolutionDTO> request = new HttpEntity<>(body, headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<SolutionDTO> result = restTemplate.exchange(uri.toUri(), HttpMethod.POST, request, SolutionDTO.class);

            SolutionDTO response = result.getBody();

            return response;
        }

        @Override
        protected void onPostExecute(final SolutionDTO response) {
        }
    }

}
