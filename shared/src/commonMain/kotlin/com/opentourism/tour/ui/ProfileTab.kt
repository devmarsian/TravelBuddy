package com.opentourism.tour.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow




object ProfileScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top, // Set the vertical arrangement to Top
            horizontalAlignment = Alignment.Start
        ) {
            Button(
                onClick = { navigator.push(ListScreen()) },
            ) {
                Text(text = "Open Privacy Policy")
            }
        }
    }
}

class ListScreen : Screen {
    val privacyPolicyText = """
        Effective Date: [Date]
        
        [Your Company Name], [Your Company Address], [Your Company Email], [Your Company Phone Number], [Your Company Website]
        
        This Privacy Policy describes how [Your Travel App Name] collects, uses, and protects the information provided by users of the [Your Travel App Name] mobile application.
        
        1. Information We Collect:
        
        1.1 Personal Information:
        - We may collect personal information such as names, email addresses, and phone numbers when users voluntarily provide such information during account registration or when using certain features of the app.
        
        1.2 Location Information:
        - [Your Travel App Name] may collect and process location information to provide location-based services such as trip planning, navigation, and local recommendations. Users can control location services through their device settings.
        
        1.3 Device Information:
        - We may collect information about the device used to access the app, including device type, operating system, and unique device identifiers.
        
        1.4 Usage Data:
        - [Your Travel App Name] may collect data about user interactions with the app, such as app usage statistics, preferences, and features accessed.
        
        2. How We Use the Information:
        
        2.1 Providing Services:
        - We use the collected information to provide and improve our travel-related services, including personalized trip recommendations, navigation, and notifications.
        
        2.2 Communication:
        - We may use the provided contact information to communicate with users about app updates, new features, and promotional offers. Users can opt-out of promotional communications.
        
        2.3 Analytics:
        - We may use analytics tools to analyze user behavior and app performance, allowing us to enhance user experience and optimize our services.
        
        3. Information Sharing:
        
        3.1 Third-Party Service Providers:
        - We may share information with third-party service providers who assist us in delivering and improving our services, subject to confidentiality agreements.
        
        3.2 Legal Requirements:
        - We may disclose information if required by law, government agencies, or to protect our rights, privacy, safety, or property.
        
        4. Data Security:
        
        - [Your Travel App Name] employs industry-standard security measures to protect user data against unauthorized access, alteration, disclosure, or destruction.
        
        5. User Controls:
        
        - Users have the right to review, update, and delete their personal information stored by [Your Travel App Name]. Users can exercise these rights through the app settings or by contacting us.
        
        6. Children’s Privacy:
        
        - [Your Travel App Name] does not knowingly collect personal information from children under the age of 13. If you become aware that your child has provided us with personal information, please contact us, and we will take steps to remove such information.
        
        7. Changes to the Privacy Policy:
        
        - [Your Travel App Name] may update this Privacy Policy periodically to reflect changes in our data practices. Users will be notified of significant updates.
        
        8. Contact Information:
        
        - For questions or concerns regarding this Privacy Policy, please contact us at [Your Company Email].
    """.trimIndent()

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(8.dp)
        ) {
            Text(
                text = privacyPolicyText,
                fontSize = 16.sp,
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navigator.pop() }
            ) {
                Text(
                    text = "Return",
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}
