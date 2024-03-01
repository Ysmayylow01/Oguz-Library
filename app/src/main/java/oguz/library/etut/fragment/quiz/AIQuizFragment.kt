package oguz.library.etut.fragment.quiz

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import oguz.library.etut.R
import oguz.library.etut.model.QuizModel

class AIQuizFragment: Fragment(){

    lateinit var soragList: ArrayList<QuizModel>
    private var index: Int = 0
    lateinit var soragModel: QuizModel

    private var dogryJogapCount: Int = 0
    private var yalnysJogapCount: Int = 0
    private var total: Int = 10
    lateinit var dowam: TextView

    lateinit var gosmak: TextView
    lateinit var sorag: TextView
    lateinit var jogap_A: TextView
    lateinit var jogap_B: TextView
    lateinit var jogap_C: TextView
    lateinit var jogap_D: TextView

    private var backPressedTime: Long = 0
    private var backToast: Toast? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ai_quiz, container, false)

        gosmak = view.findViewById(R.id.gosmak)
        sorag = view.findViewById(R.id.sorag)
        jogap_A = view.findViewById(R.id.jogap_A)
        jogap_B = view.findViewById(R.id.jogap_B)
        jogap_C = view.findViewById(R.id.jogap_C)
        jogap_D = view.findViewById(R.id.jogap_D)
        dowam = view.findViewById(R.id.Dowam)

        questionData()



        return view
    }

    private fun questionData() {
        soragList = ArrayList()

        soragList.add(
            QuizModel("",
            "",
            "",
            "",
            "",
            "")
        )
        soragList.add(
            QuizModel("",
            "",
            "",
            "",
            "",
            "")
        )
        soragList.add(
            QuizModel("",
            "",
            "",
            "",
            "",
            "")
        )



        soragList.shuffle()
        soragModel = soragList[index]

        setAllQuestions()
        onFinish()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        dowam.setOnClickListener {
            disableButton()
            onFinish()
        }

        jogap_A.setOnClickListener {
            disableButton()
            if (soragModel.jogap_A == soragModel.jogap) {
                jogap_A.background = resources.getDrawable(R.drawable.correct_option_border_bg)
                dogryJogap(jogap_A)
            } else {
                yalnysJogap(jogap_A)
            }
        }

        jogap_B.setOnClickListener {
            disableButton()
            if (soragModel.jogap_B == soragModel.jogap) {
                jogap_B.background = resources.getDrawable(R.drawable.correct_option_border_bg)
                dogryJogap(jogap_B)
            } else {
                yalnysJogap(jogap_B)
            }
        }

        jogap_C.setOnClickListener {
            disableButton()
            if (soragModel.jogap_C == soragModel.jogap) {
                jogap_C.background = resources.getDrawable(R.drawable.correct_option_border_bg)
                dogryJogap(jogap_C)
            } else {
                yalnysJogap(jogap_C)
            }
        }

        jogap_D.setOnClickListener {
            disableButton()
            if (soragModel.jogap_D == soragModel.jogap) {
                jogap_D.background = resources.getDrawable(R.drawable.correct_option_border_bg)
                dogryJogap(jogap_D)
            } else {
                yalnysJogap(jogap_D)
            }
        }
    }

    private fun disableButton() {
        jogap_A.isClickable = false
        jogap_B.isClickable = false
        jogap_C.isClickable = false
        jogap_D.isClickable = false
        dowam.isClickable = true
    }

    private fun onFinish() {
        index++
        if (index < 11) {
            soragModel = soragList[index]
            gosmak.text = "$total  / $index"
            resetBackground()
            enableButton()
            setAllQuestions()
        } else {
            oyunNetije()
        }
    }

    private fun setAllQuestions() {
        sorag.text = soragModel.sorag
        jogap_A.text = soragModel.jogap_A
        jogap_B.text = soragModel.jogap_B
        jogap_C.text = soragModel.jogap_C
        jogap_D.text = soragModel.jogap_D
    }

    private fun enableButton() {
        jogap_A.isClickable = true
        jogap_B.isClickable = true
        jogap_C.isClickable = true
        jogap_D.isClickable = true
        dowam.isClickable = false
    }

    private fun resetBackground() {
        jogap_A.background = resources.getDrawable(R.drawable.edit_shape)
        jogap_B.background = resources.getDrawable(R.drawable.edit_shape)
        jogap_C.background = resources.getDrawable(R.drawable.edit_shape)
        jogap_D.background = resources.getDrawable(R.drawable.edit_shape)
    }

    private fun dogryJogap(option: TextView) {
        option.background = resources.getDrawable(R.drawable.correct_option_border_bg)
        dogryJogapCount++

    }

    private fun yalnysJogap(option: TextView) {
        option.background = resources.getDrawable(R.drawable.wrong_option_border_bg)
        yalnysJogapCount++

    }

    private fun oyunNetije() {
        val pastStatus: String = if (dogryJogapCount == 10) {
            "Iň gowy netije"
        } else if (dogryJogapCount > 10 * 0.50) {
            "Gowy netije"
        } else {
            "Pes netije"
        }

        val builder = AlertDialog.Builder(requireContext())
            .setTitle(pastStatus)
            .setMessage("Dogry Jogap : $dogryJogapCount\n" +
                    "Yalnys jogap : $yalnysJogapCount\n" +
                    "Jemi sorag : $total")

        builder.setPositiveButton("Restart quiz") { dialogInterface: DialogInterface?, i: Int ->

            tazedenBasla()
        }
            .setNegativeButton("Cancel") { dialogInterface: DialogInterface?, i: Int ->
                parentFragmentManager.popBackStack()
            }

        val alertDialog = builder.create()
        alertDialog.show()
        val positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
        val negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
        positiveButton?.setTextColor(ContextCompat.getColor(requireContext(), R.color.textBlue))
        negativeButton?.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
    }


    private fun tazedenBasla() {
        dogryJogapCount = 0
        index = 0
        yalnysJogapCount = 0

        soragList.shuffle()
        soragModel = soragList[index]

        setAllQuestions()
        onFinish()
    }


}