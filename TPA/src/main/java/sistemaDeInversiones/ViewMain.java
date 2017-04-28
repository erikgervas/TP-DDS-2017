package sistemaDeInversiones;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.MainWindow;

public class ViewMain extends MainWindow<Empresa> {
	// Esto no deber�a tener un ViewModel, al menos por ahora. Es s�lo una vista que lleva a otras vistas.
	// Se hardcodea as� para Eclipse no rompa.
	
	VMBolsaComercial miBolsa = new VMBolsaComercial();
	
	public ViewMain() {
		super(new Empresa("ViewModel"));
	}

	@Override
	public void createContents(Panel panelPrincipal) {
		this.setTitle("Sistema de inversiones v1.0");

		new Button(panelPrincipal).setCaption("Ingresar una nueva empresa");

		new Button(panelPrincipal).setCaption("Gestionar cuentas de las empresas").onClick(() -> new ViewCuentas(this, miBolsa).open());

		new Button(panelPrincipal).setCaption("Comparar gr�ficamente empresas");

	}

	public static void main(String[] args) {
		new ViewMain().startApplication();
	}

}
