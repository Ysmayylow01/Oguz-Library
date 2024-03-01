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

class MathQuizFragment : Fragment() {

    lateinit var soragList: ArrayList<QuizModel>
    private var index: Int = 0
    lateinit var soragModel: QuizModel

    private var dogryJogapCount: Int = 0
    private var yalnysJogapCount: Int = 0
    private var total: Int = 20
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
        val view = inflater.inflate(R.layout.fragment_math_quiz, container, false)

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
            QuizModel(
                " '       ' matematikanyň bir bölümi bolup,  ol  funksiýalar barada  başlangyç düşünjeleri we derňew usullaryny öz içine alýar we  bu bölümde funksiýalara, yzygiderliliklere, funkiýalaryň predellerine, önümlerine, integrallaryna we hatarlaryna seredilýär.",
                "Matematiki analiz", "Köplük", "Funksiýa", "Argument", "Matematiki analiz"
            )
        )
        soragList.add(
            QuizModel(
                "'      ' -haýsy hem bolsa bir nyşan ýa-da düzgün boýunça häsiýetlendirilýän  islendik erkin obýektleriň toplumy.",
                "Periodik funksiýa", "Matematiki analiz", "Argument", "Köplük", "Köplük"
            )
        )
        soragList.add(
            QuizModel(
                "Üýtgeýän bir ululygyň başga bir üýtgeýän ululyga baglylygyna näme diýilýär ",
                "Köplük", "Funksiýa", "Argument", "Integral", "Funksiýa"
            )
        )
        soragList.add(
            QuizModel(
                "y=f(x)-formuladaky x-ululyga baglanşyksyz üýtgeýän ululyk ýa-da näme diýilýär",
                "Predel", "Ters funkiýa", "Argument", "Integral", "Argument"
            )
        )
        soragList.add(
            QuizModel(
                "Derejeli funksiýa, görkezijili funksiýa, logarifmik funksiýa, trigonometrik funksiýa, ters trigonometrik funksiýa haýsy funksiýalara degişli",
                "Elementar funksiýalar",
                "Ters funksiýalar",
                "Çylşyrymly funksiýalar",
                "Periodik funkiýalar",
                "Elementar funksiýalar"
            )
        )
        soragList.add(
            QuizModel(
                "Funksiýanyň kesgitleniş ýaýlasy?",
                "bu funksiýada x-ululygyň alyp biljek bahasy",
                "bu funksiýada y-ululygyň alyp biljek bahasy",
                "bu funksiýanyň bir modeli",
                "bu funksiýada baha alyp bilmeýär",
                "bu funksiýada x-ululygyň alyp biljek bahasy"
            )
        )
        soragList.add(
            QuizModel(
                "Funksiýanyň bahalar ýaýlasy",
                "bu funksiýanyň bir modeli",
                "bu funksiýada baha alyp bilmeýär",
                "bu funksiýada x-ululygyň alyp biljek bahasy",
                "bu funksiýada y-ululygyň alyp biljek bahasy",
                "bu funksiýada y-ululygyň alyp biljek bahasy"
            )
        )
        soragList.add(
            QuizModel(
                "f(-x)=f(x) şert ýerine ýetse nähili funksiýa diýilýär",
                "Jübüt funksiýa",
                "Täk funksiýa",
                "Ters funksiýa",
                "Elemtar funksiýa",
                ""
            )
        )
        soragList.add(
            QuizModel(
                "f(-x)=- f(x) şert ýerine ýetse nähili funksiýa diýilýär.",
                "Ters funksiýa",
                "Jübüt funksiýa",
                "Täk funksiýa",
                "Elemtar funksiýa",
                "Täk funksiýa"
            )
        )
        soragList.add(
            QuizModel(
                "f(x+nT)=f(x) , T≠0   şert ýerine ýetse nähili funksiýa diýilýär.",
                "Ters funksiýa",
                "Periodik funksiýa",
                "Jübüt funksiýa",
                "Täk funksiýa",
                "Periodik funksiýa"
            )
        )
        soragList.add(
            QuizModel(
                "Funksiýanyň maksimumyna, minimumyna funksiýanyň '        ' diýilýär.",
                "Funksiýanyň ekstremumy",
                "Funksiýanyň artdyrmasy",
                "Funksiýanyň önümi",
                "Funksiýanyň predeli",
                "Funksiýanyň ekstremumy"
            )
        )
        soragList.add(
            QuizModel(
                "y=f(u)  ,u=g(x),   y=f(g(x))- nähili funksiýa",
                "Ters funksiýa",
                "Periodik funksiýa",
                "Çylşyrymly funksiýa",
                "Elementar funksiýa",
                "Çylşyrymly funksiýa"
            )
        )
        soragList.add(
            QuizModel(
                "Bu matematikanyň bir bölümi bolup, durmuşda duş gelýän dürli " +
                        "meseleleriň matematiki modellerini gurup optimal çözüwlerini " +
                        "tapmaklygyň  usullaryny öwredýär.",
                "Optimallşdyrmagyň usullary",
                "Matematiki analiz",
                "Matematiki optimallaşdyrmak",
                "Öwrenmekligiň obýekti",
                "Optimallşdyrmagyň usullary",
            )
        )
        soragList.add(
            QuizModel(
                "Obýektleriň häsiýetlerini we olaryň arabaglanşyklaryny öwreniji" +
                        " tarapyndan dürli usullarda  ( grafik, formula, maket we.ş.m) " +
                        "beýan edilmegine näme diýilýär.  ",
                "Önüm",
                "Model",
                "Predel",
                "Modelirlemek",
                "Model",
            )
        )
        soragList.add(
            QuizModel(
                "Modelleri gurmaklyga we olary öwrenmeklige näme diýilýär.",
                "Predel",
                "Model",
                "Önüm",
                "Modelirlemek",
                "Modelirlemek",
            )
        )
        soragList.add(
            QuizModel(
                "Ahyrky maksada ýetmek üçin bir ýa-da birnäçe ulgamyň" +
                        " elementleri tarapyndan amala aşyrylýan käbir prosese aýdylýar.",
                "Modelirlemek",
                "Önüm",
                "Öwrenmekligiň obýekti",
                "Optimallaşdyrmagyň usullary",
                "Öwrenmekligiň obýekti",
            )
        )
        soragList.add(
            QuizModel(
                "'     ' käbir ölçegler ýa-da  belle bir kriterýalar " +
                        "esasynda iň gowy elementi ýa-da iň amatly  (optimal) çözüwi saýlamakdyr. ",
                "Optimallaşdyrmagyň usullary",
                "Matematiki optimallaşdyrmak",
                "Öwrenmekligiň obýekti",
                "Modelirlemek",
                "Matematiki optimallaşdyrmak"
            )
        )
        soragList.add(
            QuizModel(
                "Optimallyk kriterisi näme",
                "çäklendirmeler ulgamynyň matematiki görnüşi",
                "Öwrenmekligiň obýekti",
                "Modelirlemek",
                "bu ykdysady manysy bar bolan käbir görkeziji",
                "bu ykdysady manysy bar bolan käbir görkeziji"
            )
        )
        soragList.add(
            QuizModel(
                "'      ' modeliň faktorlaryny matematiki dilde baglanşdyrýar.",
                "Ters funksiýa",
                "Maksat funksiýa",
                "Jübüt funksiýa",
                "Täk funkiýa",
                "Maksat funksiýa"
            )
        )
        soragList.add(
            QuizModel(
                "'     '  obýektiň esasy içerki we daşarky häsiýetlerini göz öňünde tutýar.",
                "Çäklendirmeler ulgamy",
                "Arabaglanyşyk deňlemeleri",
                "Matematiki modeliň çözüwi",
                "Optimal çözüw",
                "Çäklendirmeler ulgamy"
            )
        )
        soragList.add(
            QuizModel(
                "Matematikanyň näbellileriň arasyndaky baglanyşyklar we maksat funksiýasy " +
                        "çyzykly funksiýa bolanda ekstremum tapmak meselesi bilen iş salyşylýan bölümine  näme diýilýär.",
                "Çäklendirmeler ulgamy",
                "Optimal çözüw",
                "Çyzykly programmirlemek",
                "Matematiki modeliň çözüwi",
                "Çyzykly programmirlemek"
            )
        )
        soragList.add(
            QuizModel(
                "'    ' bu funksiýada baglanşyksyz üýtgeýän ululygyň derejesi bire deň bolmaly.",
                "Jübüt funksiýa",
                "Täk funksiýa",
                "Ters funksiýa",
                "Çyzykly funksiýa",
                "Çyzykly funksiýa"
            )
        )
        soragList.add(
            QuizModel(
                "'    ' bu  ugradyjy nokatdan sarp ediji nokatlara ýük daşamaklygyň iň az çykdaýjy meýilnamasyny tapmak meselesi.",
                "Ulag meselesi",
                "Obýekt",
                "Çyzykly funksiýa",
                "Matematiki analiz",
                "Ulag meselesi"
            )
        )
        soragList.add(
            QuizModel(
                "Uzynlygy we ugry görkezilen kesime näme diýilýär.",
                "Kesim",
                "Wektor",
                "Obýekt",
                "Funksiýa",
                "Wektor"
            )
        )
        soragList.add(
            QuizModel(
                "Eger ululyk özüniň san bahasy bilen häsiýetlendirilýän bolsa, oňa näme diýilýär.",
                "Wektor",
                "Komponent ululyk",
                "Wektor ululyk",
                "Skalýar ululyk",
                "Skalýar ululyk"
            )
        )
        soragList.add(
            QuizModel(
                "Eger ululyk özüniň san bahasy hem-de ugry bilen häsiýetlendirilýän bolsa, oňa näme diýilýär",
                "Wektor",
                "Komponent ululyk",
                "Wektor ululyk",
                "Skalýar ululyk",
                "Wektor ululyk"
            )
        )
        soragList.add(
            QuizModel(
                "Maksat funksiýasy näme?",
                "Minimumy ýa-da maksimumy gözlenýän funksiýa",
                "Minimumy gözlenýän funksiýa",
                "Maksimumy gözlenýän funksiýa",
                "Funksiýa",
                "Minimumy ýa-da maksimumy gözlenýän funksiýa"
            )
        )
        soragList.add(
            QuizModel(
                "Diňe bir bagly däl üýtgeýän ululyk boýunça önümleri saklaýan deňlemelere näme diýilýär.",
                "Diskriminat",
                "Önüm",
                "Ady differensial deňlemeler",
                "Deňlemeler",
                "Ady differensial deňlemeler "
            )
        )
        soragList.add(
            QuizModel(
                "Deňleme näme?",
                "içinde näbelli ululygy saklaýar",
                "içinde harp saklaýar",
                "içinde san saklaýar",
                "içinde ululyk saklaýar",
                "içinde näbelli ululygy saklaýar"
            )
        )
        soragList.add(
            QuizModel(
                "Içinde näbelli funksiýanyň differensialyny saklaýan deňlemä näme diýilýär.",
                "Diskriminat",
                "Önüm",
                "Ady differensial deňlemeler",
                "Differensiýal deňlemeler",
                "Differensiýal deňlemeler"
            )
        )
        soragList.add(
            QuizModel(
                "Köplük näme?",
                "Predmetleriň ýa-da obýektleriň kesgitli toplumy",
                "Predmetleriň toplumy",
                "Obýektleriň toplumy",
                "Sanlaryň toplumy",
                "Predmetleriň ýa-da obýektleriň kesgitli toplumy"
            )
        )
        soragList.add(
            QuizModel(
                "Içinde hiç hili elementi ýok bolan köplüge näme diýilýär?",
                "Köplük",
                "Boş köplük",
                "Doly köplük",
                "0 Köplük",
                "Boş köplük"
            )
        )
        soragList.add(
            QuizModel(
                "Gözegçilik ýada tejribe dowamynda ýüze çykmagy hem, ýüze çykmazlygy hem mümkin bolan waka näme diýilýär?. Meselem : oklanan okuň tora düşmegi.",
                "Tötän waka",
                "Hökmany waka",
                "Waka",
                "Synag",
                "Tötän waka"
            )
        )
        soragList.add(
            QuizModel(
                "Synagda ýüze çykjagyny öňünden tassyklap bolýan waka näme diýilýär. Meselem: iki sany kubjagaz oklananda düşen oçkalaryň jeminiň 12-den uly bolmazlygy.",
                "Tötän waka",
                "Hökmany waka",
                "Waka",
                "Synag",
                "Hökmany waka"
            )
        )
        soragList.add(
            QuizModel(
                "Sanlar hatarynyň  jeminiň goşulyjylarynyň sanyna bolan paýa näme aýdylýar. ",
                "Diskreiminant",
                "Arifmetika",
                "Orta arifmetika",
                "Mediana",
                "Orta arifmetika"
            )
        )
        soragList.add(
            QuizModel(
                "Hataryň modasy näme?",
                "Garalýan hatarda iň köp gaýtalanýan san",
                "Gaýtalanýan san",
                "Köp san",
                "Matrisa",
                "Garalýan hatarda iň köp gaýtalanýan san"
            )
        )
        soragList.add(
            QuizModel(
                "Logika sözi näme diýmekligi aňladýar?",
                "pikir, akyl",
                "logika",
                "çözmek",
                "kyn",
                "pikir, akyl"
            )
        )
        soragList.add(
            QuizModel(
                "Çyndygy ýada ýalandygy barada belli bir pikir aýtmagyň manysy bar bolan her bir tassyklama näme diýilýär.",
                "logika",
                "pikir aýtma",
                "funksiýa",
                "san",
                "pikir aýtma"
            )
        )
        soragList.add(
            QuizModel(
                "Diskret sözi nämani aňladýar?",
                "üznüksizlik diýlen düşünjä ters bolan düşünje",
                "ters düşünje",
                "üznüksizlik",
                "pikir aýtma",
                "üznüksizlik diýlen düşünjä ters bolan düşünje"
            )
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
            .setMessage(
                "Dogry Jogap : $dogryJogapCount\n" +
                        "Yalnys jogap : $yalnysJogapCount\n" +
                        "Jemi sorag : $total"
            )

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