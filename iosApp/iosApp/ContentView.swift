import SwiftUI
import shared

struct ContentView: View {


 var body: some View {
   ComposeView().ignoresSafeArea(.all)
 }
}

struct ContentView_Previews: PreviewProvider {
 static var previews: some View {
  ContentView()
 }
}
struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}