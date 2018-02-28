package m.com.lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button calculateBtn;
    Button resetBtn;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d("Bhavin","This is a onBackPressed msg");
        android.os.Process.killProcess(android.os.Process.myPid());
       // System.exit(0);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d("Bhavin","This is a destroyed msg");

        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d("Bhavin","This is a Pause msg");
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d("Bhavin","This is a Resume msg");
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d("Bhavin","This is a Start msg");
    }


    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d("Bhavin","This is a stop msg");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Bhavin","This is a debug msg");
        setContentView(R.layout.activity_main);

        calculateBtn = (Button)findViewById(R.id.calButton);
        calculateButtonListener();

        resetBtn = (Button)findViewById(R.id.button);
        resetButtonListener();

        resetBtn.callOnClick();

    }

    /* Method to hide result fields when the app loads initially */

    private void hideResultFields()
    {
        TextView totalCostLabel = (TextView) findViewById(R.id.textView5);
        totalCostLabel.setVisibility(View.INVISIBLE);

        TextView taxAmountLabel = (TextView) findViewById(R.id.textView6);
        taxAmountLabel.setVisibility(View.INVISIBLE);

        TextView tipAmountLabel = (TextView) findViewById(R.id.textView7);
        tipAmountLabel.setVisibility(View.INVISIBLE);

        TextView totalCostVal = (TextView) findViewById(R.id.textView8);
        totalCostVal.setVisibility(View.INVISIBLE);

        TextView taxAmountVal = (TextView) findViewById(R.id.textView9);
        taxAmountVal.setVisibility(View.INVISIBLE);

        TextView tipAmountVal = (TextView) findViewById(R.id.textView10);
        tipAmountVal.setVisibility(View.INVISIBLE);

        TextView errMsg = (TextView) findViewById(R.id.textView11);
        errMsg.setVisibility(View.INVISIBLE);
    }

    /* Method to show result fields after total amount calculation */

    private void showResultFields()
    {
        TextView totalCostLabel = (TextView) findViewById(R.id.textView5);
        totalCostLabel.setVisibility(View.VISIBLE);

        TextView taxAmountLabel = (TextView) findViewById(R.id.textView6);
        taxAmountLabel.setVisibility(View.VISIBLE);

        TextView tipAmountLabel = (TextView) findViewById(R.id.textView7);
        tipAmountLabel.setVisibility(View.VISIBLE);

        TextView totalCostVal = (TextView) findViewById(R.id.textView8);
        totalCostVal.setVisibility(View.VISIBLE);

        TextView taxAmountVal = (TextView) findViewById(R.id.textView9);
        taxAmountVal.setVisibility(View.VISIBLE);

        TextView tipAmountVal = (TextView) findViewById(R.id.textView10);
        tipAmountVal.setVisibility(View.VISIBLE);
    }

    /* Method to implement listener for calculate button */

    private void calculateButtonListener()
    {
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText billAmount = (EditText) findViewById(R.id.editText3);
                EditText taxAmount = (EditText) findViewById(R.id.editText);
                EditText tipAmount = (EditText) findViewById(R.id.editText2);

                billAmount.clearFocus();
                taxAmount.clearFocus();
                tipAmount.clearFocus();

                /* Check values for null before proceeding for total amount calculation */

                if (billAmount.getText() == null || "".equalsIgnoreCase(billAmount.getText().toString()))
                {
                    TextView errMsg = (TextView) findViewById(R.id.textView11);
                    errMsg.setVisibility(View.VISIBLE);
                    errMsg.setText("*Provide all values");
                    return;
                }

                if (taxAmount.getText() == null || "".equalsIgnoreCase(taxAmount.getText().toString()))
                {
                    TextView errMsg = (TextView) findViewById(R.id.textView11);
                    errMsg.setVisibility(View.VISIBLE);
                    errMsg.setText("*Provide all values");
                    return;
                }

                if (tipAmount.getText() == null || "".equalsIgnoreCase(tipAmount.getText().toString()))
                {
                    TextView errMsg = (TextView) findViewById(R.id.textView11);
                    errMsg.setVisibility(View.VISIBLE);
                    errMsg.setText("*Provide all values");
                    return;
                }

                TextView errMsg = (TextView) findViewById(R.id.textView11);
                errMsg.setVisibility(View.INVISIBLE);
                errMsg.setText("");

                /* Getting input values and converting to necessary data type*/

                int iBillAmount = new Integer(billAmount.getText().toString());
                double iTaxAmount = new Double(taxAmount.getText().toString());
                double iTipAmount = new Double(tipAmount.getText().toString());

                calculateResult(iBillAmount, iTaxAmount, iTipAmount);

            }
        });
    }

    /* Method to implement listener for reset button */

    private void resetButtonListener()
    {
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                hideResultFields();

                EditText billAmount = (EditText) findViewById(R.id.editText3);
                billAmount.setText("");

                EditText taxAmount = (EditText) findViewById(R.id.editText);
                taxAmount.setText("");

                EditText tipAmount = (EditText) findViewById(R.id.editText2);
                tipAmount.setText("");
            }
        });
    }

    /* Method to calculate total amount, tip amount and tax amount and setting values on screen */

    private void calculateResult(int cost, double tax, double tip)
    {
        double taxAmt = Math.round(cost * (tax/100));
        double tipAmt = Math.round(cost * (tip/100));
        double totalCost = Math.round(cost + taxAmt + tipAmt);

        TextView totalCostVal = (TextView) findViewById(R.id.textView8);
        totalCostVal.setText(""+totalCost);

        TextView taxAmountVal = (TextView) findViewById(R.id.textView9);
        taxAmountVal.setText(""+taxAmt);

        TextView tipAmountVal = (TextView) findViewById(R.id.textView10);
        tipAmountVal.setText(""+tipAmt);

        showResultFields();
    }
}
