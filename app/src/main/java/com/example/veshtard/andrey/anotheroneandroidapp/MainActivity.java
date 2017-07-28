package com.example.veshtard.andrey.anotheroneandroidapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private int scoreTeamA = 0;
    private int scoreTeamB = 0;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpButtons();
        setTeamNames();
        refreshScore();
    }

    /**
     * Add 3 point to score
     */
    private void addThreePoints(final View view) {
        final int team = ((View) view.getParent()).getId();

        if (team == R.id.first_team_data) {
            scoreTeamA += 3;
        }
        if (team == R.id.second_team_data) {
            scoreTeamB += 3;
        }
    }

    /**
     * Add 2 point to score
     */
    private void addTwoPoints(final View view) {
        final int team = ((View) view.getParent()).getId();

        if (team == R.id.first_team_data) {
            scoreTeamA += 2;
        }
        if (team == R.id.second_team_data) {
            scoreTeamB += 2;
        }
    }

    /**
     * Add 1 point to score
     */
    private void addOnePoint(final View view) {
        final int team = ((View) view.getParent()).getId();

        if (team == R.id.first_team_data) {
            scoreTeamA += 1;
        }
        if (team == R.id.second_team_data) {
            scoreTeamB += 1;
        }
    }

    /**
     * Reset both teams score to 0
     */
    private void reset() {
        scoreTeamA = 0;
        scoreTeamB = 0;
    }

    /**
     * Displays the given score for Teams
     */
    private void refreshScore() {
        final TextView scoreViewA = findViewById(R.id.first_team_data).findViewById(R.id.team_score);
        final TextView scoreViewB = findViewById(R.id.second_team_data).findViewById(R.id.team_score);

        scoreViewA.setText(String.format(Locale.getDefault(),"%d", scoreTeamA));
        scoreViewB.setText(String.format(Locale.getDefault(), "%d", scoreTeamB));
    }

    private void setTeamNames() {
        final TextView teamA = findViewById(R.id.first_team_data).findViewById(R.id.team_name);
        final TextView teamB = findViewById(R.id.second_team_data).findViewById(R.id.team_name);

        setTextFor(teamA, R.string.firstTeamName);
        setTextFor(teamB, R.string.secondTeamName);
    }

    private void setTextFor(final TextView textView, final int textId) {
        textView.setText(textId);
    }

    private Map<Button, Integer> collectButtons() {
        final LinearLayout teamA = (LinearLayout) findViewById(R.id.first_team_data);
        final LinearLayout teamB = (LinearLayout) findViewById(R.id.second_team_data);

        final Map<Button, Integer> buttons = new HashMap<>();
        buttons.put((Button) teamA.findViewById(R.id.one_point_button), R.string.buttonName1p);
        buttons.put((Button) teamA.findViewById(R.id.two_points_button), R.string.buttonName2p);
        buttons.put((Button) teamA.findViewById(R.id.three_points_button), R.string.buttonName3p);
        buttons.put((Button) teamB.findViewById(R.id.one_point_button), R.string.buttonName1p);
        buttons.put((Button) teamB.findViewById(R.id.two_points_button), R.string.buttonName2p);
        buttons.put((Button) teamB.findViewById(R.id.three_points_button), R.string.buttonName3p);
        buttons.put((Button) findViewById(R.id.reset_button), R.string.buttonNameReset);

        return buttons;
    }

    private void setUpButtons() {
        final Map<Button, Integer> buttons = collectButtons();

        for (final Map.Entry<Button, Integer> pair : buttons.entrySet()) {
            final Button button = pair.getKey();
            final int text = pair.getValue();

            button.setText(text);
            button.setOnClickListener(new CustomOnClickListener());

            if (button.getId() == R.id.reset_button) {
                final MarginLayoutParams marginLayoutParams = (MarginLayoutParams) button.getLayoutParams();
                marginLayoutParams.bottomMargin = 32;
                final LayoutParams layoutParams = button.getLayoutParams();
                layoutParams.width = LayoutParams.WRAP_CONTENT;
            }
        }
    }

    private class CustomOnClickListener implements Button.OnClickListener {
        @Override
        public void onClick(final View view) {
            switch (view.getId()) {
                case R.id.one_point_button:
                    addOnePoint(view);
                    break;
                case R.id.two_points_button:
                    addTwoPoints(view);
                    break;
                case R.id.three_points_button:
                    addThreePoints(view);
                    break;
                case R.id.reset_button:
                    reset();
                    break;
                default:
            }

            refreshScore();
        }
    }
}
