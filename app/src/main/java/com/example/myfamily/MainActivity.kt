package com.example.myfamily

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfamily.ui.theme.MyFamilyTheme

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            MyFamilyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyFamily( modifier = Modifier)
                }
            }
        }
    }
}

@Composable
fun MyFamily(modifier: Modifier =  Modifier)
{
    var iteration by remember { mutableStateOf(0) }
    var data by remember {
        mutableStateOf(listOf(R.drawable.myfam0, R.string.my_fam_desc0, R.string.my_fam_year0))
    }
    var contentDescription by remember { mutableStateOf(R.string.my_fam_desc0) }
    var date by remember { mutableStateOf(R.string.my_fam_year0) }
    var imageTitle by remember { mutableStateOf("Title") }
    var place by remember { mutableStateOf("Where") }

    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
                    .padding(24.dp)
                    .background(Color.White)
                    .shadow(elevation = 4.dp)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    painter = painterResource(id = data[0]),
                    contentDescription = stringResource(id = data[1]),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .background(Color.LightGray)
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = data[1]),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Light
                )
                Row {
                    Text(
                        text = place,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "(${stringResource(id = data[2])})",
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    modifier = Modifier
                        .weight(1F)
                        .padding(16.dp),
                    onClick = {
                        if (iteration <= 0) iteration = 0 else iteration--
                        data = handleButtonTap(iteration)
                    }
                ) {
                    Text(stringResource(R.string.previous))
                }
                Button(
                    modifier = Modifier
                        .weight(1F)
                        .padding(16.dp),
                    onClick = {
                        if (iteration >= 4 ) iteration = 4 else iteration++
                        data = handleButtonTap(iteration)
                    }
                ) {
                    Text(text = stringResource(R.string.next))
                }
            }
        }
    }
}

 fun handleButtonTap(currentPos: Int): List<Int> {
    return selectImage(currentPos)
}
fun selectImage(currentPos: Int): List<Int> {
    return when(currentPos) {
        0 -> listOf(R.drawable.myfam0, R.string.my_fam_desc0, R.string.my_fam_year0)
        1-> listOf(R.drawable.myfam1, R.string.my_fam_desc1, R.string.my_fam_year1)
        2 -> listOf(R.drawable.myfam2, R.string.my_fam_desc2, R.string.my_fam_year2)
        3 -> listOf(R.drawable.myfam3, R.string.my_fam_desc3, R.string.my_fam_year3)
        else -> listOf(R.drawable.myfam4, R.string.my_fam_desc4, R.string.my_fam_year4)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview()
{
    MyFamilyTheme {
        MyFamily(modifier = Modifier)
    }
}