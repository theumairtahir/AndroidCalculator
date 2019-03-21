package ut786.clone.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import ut786.clone.calculator.ArithmeticModel.ArithmeticTree;
import ut786.clone.calculator.ParserModel.Exceptions.ArithmeticParser;
import ut786.clone.calculator.ParserModel.Exceptions.LettersAlreadyAddedException;
import ut786.clone.calculator.ParserModel.Exceptions.MissingEdgeException;
import ut786.clone.calculator.ParserModel.Exceptions.NoInitialStateFoundException;
import ut786.clone.calculator.ParserModel.Exceptions.NoMoreStartStateException;
import ut786.clone.calculator.ParserModel.Exceptions.NoSuchLetterFoundException;
import ut786.clone.calculator.ParserModel.Token;

public class MainActivity extends AppCompatActivity {
    EditText edtDisplay;
    float total=0;
    char op;
    TextView txtHistory;
    boolean firstFlag;
    Button equals;
    Button btnPoint;

    @Override
    protected void onStart() {
        super.onStart();
        firstFlag=true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtDisplay=findViewById(R.id.edtDisplay);
        txtHistory=findViewById(R.id.txtHistory);
        equals=findViewById(R.id.btnEql);
        final ArithmeticTree tree;
        try {
            ArithmeticParser parser = new ArithmeticParser();
            List<Token> lstToken = parser.GenerateTokens(edtDisplay.getText().toString());
            tree = new ArithmeticTree(lstToken);
            equals.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edtDisplay.setText(Double.toString(tree.Calculate()));
                }
            });
        }
        catch (Exception e){
            Log.e("Error",e.getMessage());
        }
    }
    public void NumClk(View view) {
        Button btnNum=(Button)view;
        if(!firstFlag)
            edtDisplay.setText(edtDisplay.getText()+btnNum.getText().toString());
        else
            edtDisplay.setText(((Button) view).getText().toString());
        firstFlag=false;
    }
    public void OpClk(View view) {
        NumClk(view);
    }
}
