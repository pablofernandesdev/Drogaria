function verificar(xhr, status, args, dlg, tbl) {
	if (args.validationFailed) {
		PF(dlg).jq.effect("shake", {
			times : 5
		}, 100); //verifica se o usuario informou, se nao informou permanece na tela
	} else {
		PF(dlg).hide();
		PF(tbl).clearFilters(); //se der certo limpa os filtros da tabela
	}
}