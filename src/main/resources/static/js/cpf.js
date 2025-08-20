  // --- Validador CPF (remove pontuação + aplica algoritmo) ---
  function validarCPF(cpf) {
    if (!cpf || cpf.length<1) return true;
    cpf = cpf.replace(/[^\d]+/g, '');
    if (cpf.length !== 11) return false;

    // Rejeita sequências tipo 00000000000, 11111111111...
    if (/^(\d)\1{10}$/.test(cpf)) return false;

    let soma = 0, resto;

    // 1º dígito verificador
    for (let i = 1; i <= 9; i++) soma += parseInt(cpf.substring(i-1, i)) * (11 - i);
    resto = (soma * 10) % 11;
    if (resto === 10 || resto === 11) resto = 0;
    if (resto !== parseInt(cpf.substring(9, 10))) return false;

    // 2º dígito verificador
    soma = 0;
    for (let i = 1; i <= 10; i++) soma += parseInt(cpf.substring(i-1, i)) * (12 - i);
    resto = (soma * 10) % 11;
    if (resto === 10 || resto === 11) resto = 0;
    if (resto !== parseInt(cpf.substring(10, 11))) return false;

    return true;
  }
