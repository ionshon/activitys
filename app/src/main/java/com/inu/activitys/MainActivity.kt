package com.inu.activitys


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.inu.activitys.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    // lazy 를 사용해서 처음 호출될 때 초기화 되도록 설정
    // binding 이 프로퍼티로 선언되어 있기 때문에 액티비티 전체에서 호출 가능
    // lazy, lateinit : 변수 선언 후 늦은 초기화시 null로 선언할 필요가 없을 때 사용
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private lateinit var getResultText: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)  // * setContentView에는 binding.root를 꼭 전달

        // 바뀐 registerForActivityResult API 구현방법
        getResultText = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result ->
                    if (result.resultCode == RESULT_OK) {
                        val message = result.data?.getStringExtra("STRING_INTENT_KEY")
                        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                    }
        }
        binding.btnStart.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("from1", "hello bundle")
            intent.putExtra("from2", 2020)
            getResultText.launch(intent)
        }
/*  depricated startActivityForResult()
        var intent = Intent(this, SubActivity::class.java)
        intent.putExtra("from1", "hello bundle")
        intent.putExtra("from2", 2020)

        binding.btnStart.setOnClickListener { startActivityForResult(intent, 99) }
        */
    // 2부. 컨테이너
        val data = listOf("- 선택하세요 -", "1월", "2월", "3월", "4월", "5월", "6월")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                binding.result.text = data[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) { }
        }
    }

    /*  depricated onActivityResult()
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                99 -> {
                    val message = data?.getStringExtra("returnValue")
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                }

            }
        }
    }
    */
}