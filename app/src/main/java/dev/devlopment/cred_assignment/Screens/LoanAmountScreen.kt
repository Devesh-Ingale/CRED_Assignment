    package dev.devlopment.cred_assignment.Screens

    import android.widget.Toast
    import androidx.compose.foundation.Canvas
    import androidx.compose.foundation.background
    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.gestures.detectDragGestures
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Box

    import androidx.compose.foundation.layout.Column

    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxHeight
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.foundation.layout.wrapContentHeight
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.foundation.text.BasicTextField
    import androidx.compose.foundation.text.KeyboardOptions
    import androidx.compose.material3.Button
    import androidx.compose.material3.Card
    import androidx.compose.material3.CardDefaults
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.OutlinedButton
    import androidx.compose.material3.OutlinedTextField
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.geometry.Offset
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.graphics.StrokeCap
    import androidx.compose.ui.graphics.drawscope.Stroke
    import androidx.compose.ui.input.pointer.pointerInput
    import androidx.compose.ui.platform.LocalContext
    import androidx.compose.ui.platform.LocalSoftwareKeyboardController
    import androidx.compose.ui.text.TextStyle
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.text.input.KeyboardType
    import androidx.compose.ui.text.input.TextFieldValue
    import androidx.compose.ui.text.style.TextAlign
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import dev.devlopment.cred_assignment.ViewModels.LoanViewModel
    import kotlin.math.atan2
    import kotlin.math.cos
    import kotlin.math.sin

    @Composable
    fun LoanAmountScreen( viewModel: LoanViewModel ,onProceed: () -> Unit) {
        val apiData = viewModel.apiData.value
        val item = apiData?.items?.get(0)?.open_state?.body
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1E1E1E), shape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
        ) {
            // Scrollable content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                    //.padding(bottom = 80.dp), // Leave space for the button at the bottom
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Text(
                    text = item?.title ?: "",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 16.dp)
                )



                Text(
                    text = "move the dial and set any amount you need up to ₹487,891",
                    color = Color.Gray,
                    fontSize = 16.sp
                )


                CreditAmountCard()



                Spacer(modifier = Modifier.weight(1f))


                // Bottom Proceed Button
                Button(
                    onClick = onProceed,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Proceed to EMI selection", fontSize = 18.sp)
                }
            }
        }
    }



    @Composable
    fun CreditAmountCard() {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(250.dp)
                ) {
                    var sliderValue by remember { mutableStateOf(150000f) }
                    var isEditing by remember { mutableStateOf(false) }
                    var textFieldValue by remember {
                        mutableStateOf(TextFieldValue("₹${String.format("%,d", sliderValue.toInt())}"))
                    }
                    val keyboardController = LocalSoftwareKeyboardController.current

                    CircularSlider(
                        value = sliderValue,
                        maxValue = 487891f,
                        onValueChange = { newValue ->
                            sliderValue = newValue
                            if (!isEditing) {
                                textFieldValue = TextFieldValue("₹${String.format("%,d", newValue.toInt())}")
                            }
                        }
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "credit amount",
                            color = Color.Gray,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        BasicTextField(
                            value = textFieldValue,
                            onValueChange = { newValue ->
                                isEditing = true
                                textFieldValue = newValue
                                val numericValue = newValue.text.filter { it.isDigit() }
                                if (numericValue.isNotEmpty()) {
                                    val newAmount = numericValue.toLongOrNull()?.coerceIn(0, 300000)
                                    if (newAmount != null) {
                                        sliderValue = newAmount.toFloat()
                                    }
                                }
                            },
                            textStyle = TextStyle(
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                color = Color.Black
                            ),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .clickable {
                                    isEditing = true
                                    keyboardController?.show()
                                }
                        )

                        Text(
                            text = "@1.04% monthly",
                            color = Color.Green,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }

                Text(
                    text = "stash is instant, money will be credited within seconds.",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }

    @Composable
    fun CircularSlider(
        value: Float,
        maxValue: Float,
        onValueChange: (Float) -> Unit
    ) {
        val minValue = 0f
        val radius = 110.dp
        val strokeWidth = 32f
        val handleRadius = 45f

        // Calculate sweep angle based on current value
        val angle = (value / maxValue) * 360f

        Canvas(
            modifier = Modifier
                .size(250.dp)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = { },
                        onDragEnd = { },
                        onDragCancel = { }
                    ) { change, dragAmount ->
                        change.consume()

                        val center = Offset(size.width / 2f, size.height / 2f)
                        val touchPoint = change.position

                        val theta = atan2(
                            touchPoint.y - center.y,
                            touchPoint.x - center.x
                        )

                        var degrees = Math.toDegrees(theta.toDouble()).toFloat()
                        if (degrees < 0) degrees += 360f

                        // Smooth the value change
                        val newValue = (degrees / 360f) * maxValue
                        onValueChange(newValue.coerceIn(minValue, maxValue))
                    }
                }
        ) {
            val center = Offset(size.width / 2, size.height / 2)
            val arcRadius = size.width / 2 - strokeWidth / 4

            // Draw background track first
            drawArc(
                color = Color(0xFFFAF0F0),
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )

            // Draw progress arc
            drawArc(
                color = Color(0xFFFF7B7B),
                startAngle = -90f,
                sweepAngle = angle,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )

            // Calculate handle position
            val handleAngle = Math.toRadians((angle - 90).toDouble())
            val handleX = (arcRadius * cos(handleAngle)).toFloat() + center.x
            val handleY = (arcRadius * sin(handleAngle)).toFloat() + center.y

            // Draw handle components
            val greenRectWidth = handleRadius * 0.8f
            val greenRectHeight = handleRadius * 0.15f

            // Draw outer orange circle stroke
            drawCircle(
                color = Color(0xFFFFA500),
                radius = handleRadius,
                center = Offset(handleX, handleY),
                style = Stroke(width = 4f)
            )

            // Draw filled black circle inside
            drawCircle(
                color = Color.Black,
                radius = handleRadius - 4f,
                center = Offset(handleX, handleY)
            )

            // Draw green rectangle in the middle
            drawRect(
                color = Color(0xFF4CAF50),
                topLeft = Offset(
                    handleX - greenRectWidth / 2,
                    handleY - greenRectHeight / 2
                ),
                size = androidx.compose.ui.geometry.Size(
                    width = greenRectWidth,
                    height = greenRectHeight
                )
            )
        }
    }

    @Preview
    @Composable
    fun LoanAmountScreenPreview() {
        LoanAmountScreen(viewModel = LoanViewModel()) {  }
    }