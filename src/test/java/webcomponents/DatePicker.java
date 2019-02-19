package webcomponents;

import org.openqa.selenium.By;

import webcomponents.Button;
import webcomponents.Combo;
import webcomponents.Component;

public class DatePicker extends Component {

	private Combo comboMes = new Combo(By.className("ui-datepicker-month"), "", true);
	private Combo comboAno = new Combo(By.className("ui-datepicker-year"), "", true);

	public DatePicker(By elementAddress,  String propertyBind, boolean loadPanel) {
		super(elementAddress, propertyBind, loadPanel);
	}

	public void selecionaData(String data) {
		String[] partes = data.split("/");
		String dia = partes[0];
		String mes = partes[1];
		String ano = partes[2];
		Button botaoDia = new Button(By.xpath("//a[contains(text(),'" + dia + "')]"), "", true);

		comboMes.select(mes);
		comboAno.select(ano);
		botaoDia.click();

	}

	public void selecionaData(String dia, String mes, String ano) {
		Button botaoDia = new Button(By.xpath("//a[contains(text(),'" + dia + "')]"), "", true);

		comboMes.select(mes);
		comboAno.select(ano);
		botaoDia.click();
	}



}
