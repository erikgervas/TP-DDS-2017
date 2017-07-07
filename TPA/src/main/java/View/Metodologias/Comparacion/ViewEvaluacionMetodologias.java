package View.Metodologias.Comparacion;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;

import Modelo.Empresa.Empresa;
import Modelo.Metodologias.Comparadores.ComparadorMultiple;
import View.Metodologias.VMEvaluacionGlobal;
import View.Metodologias.ViewEvaluacionGlobal;

public class ViewEvaluacionMetodologias extends Window<VMEvaluacionMetodologias>  {
		
		public ViewEvaluacionMetodologias(WindowOwner panelPrincipal, VMEvaluacionMetodologias vmEvaluacionMetodologias) {
			super(panelPrincipal, vmEvaluacionMetodologias);
		}

		@Override
		public void createContents(Panel panelPrincipal) {
			this.setTitle("Evaluacion de Metodologías");
			
			
			new Label(panelPrincipal).setText("Seleccione el tipo de evaluacion:");
			new Label(panelPrincipal);
			
			new Button(panelPrincipal).setCaption("Evaluacion unitaria").onClick(() -> new ViewEvaluacionUnitaria(this,new VMEvaluacionUnitaria()).open());
			new Label(panelPrincipal);
			
			new Button(panelPrincipal).setCaption("Evaluacion dual").onClick(() -> new ViewEvaluacionDual(this,new VMEvaluacionDual()).open());
			new Label(panelPrincipal);
			
			new Button(panelPrincipal).setCaption("Evaluacion multiple").onClick(() -> new ViewEvaluacionMultiple(this,new VMEvaluacionMultiple()).open());
			new Label(panelPrincipal);
			
			new Button(panelPrincipal).setCaption("Evaluacion global").onClick(() -> new ViewEvaluacionGlobal(this, new VMEvaluacionGlobal()).open());
			new Label(panelPrincipal);
			
			
		}
}
