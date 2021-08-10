package ronisvonn.gomes.businesscard.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.jaredrummler.materialspinner.MaterialSpinner
import ronisvonn.gomes.businesscard.App
import ronisvonn.gomes.businesscard.R
import ronisvonn.gomes.businesscard.data.BusinessCard
import ronisvonn.gomes.businesscard.databinding.ActivityAddBusinessCardBinding


class AddBusinessCardActivity : AppCompatActivity() {

    lateinit var cor : String;

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()


        val spinner = findViewById<View>(R.id.spinner) as MaterialSpinner
        spinner.setItems("Selecione uma Cor do Card", "Azul", "Cinza", "Verde")
        spinner.setOnItemSelectedListener { view, position, id, item ->

            if (item.equals("Azul")) {
                cor = "#6495ED"
            } else if (item.equals("Cinza")) {
                cor = "#708090"
            } else if (item.equals("Verde")) {
                cor = "#3CB371"
            } else {
                cor = "B22222"
            }

        }
    }

    private fun insertListeners() {
        binding.btnConfirm.setOnClickListener {
            val businessCard = BusinessCard(
                nome = binding.tilNome.editText?.text.toString(),
                empresa = binding.tilEmpresa.editText?.text.toString(),
                telefone = binding.tilTelefone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                fundoPersonalizado = cor


            )
            mainViewModel.insert(businessCard)
            Toast.makeText(this, R.string.label_show_success, Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btnClose.setOnClickListener {
            finish()
        }
    }
}