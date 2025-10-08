import React, { useState } from 'react';
import { View, ScrollView, StyleSheet, Alert } from 'react-native';
import { 
  TextInput, 
  Button, 
  Card, 
  Title, 
  Paragraph,
  RadioButton,
  HelperText
} from 'react-native-paper';
import { SafeAreaView } from 'react-native-safe-area-context';
import DatePicker from 'react-native-date-picker';
import RNPickerSelect from 'react-native-picker-select';
import AsyncStorage from '@react-native-async-storage/async-storage';

const RegisterScreen = ({ navigation }) => {
  const [formData, setFormData] = useState({
    nome: '',
    email: '',
    telefone: '',
    sexo: '',
    tipoSanguineo: '',
    endereco: '',
    bairro: '',
    cidade: '',
    estado: '',
    dataNascimento: '',
    pai: '',
    mae: '',
    cursoDesejado: 'Brigadista Mirim',
    comoSoube: '',
  });

  const [errors, setErrors] = useState({});
  const [showDatePicker, setShowDatePicker] = useState(false);

  const estados = [
    { label: 'Rio de Janeiro', value: 'RJ' },
    { label: 'São Paulo', value: 'SP' },
    { label: 'Minas Gerais', value: 'MG' },
    { label: 'Bahia', value: 'BA' },
    { label: 'Paraná', value: 'PR' },
    { label: 'Rio Grande do Sul', value: 'RS' },
    { label: 'Pernambuco', value: 'PE' },
    { label: 'Ceará', value: 'CE' },
    { label: 'Pará', value: 'PA' },
    { label: 'Santa Catarina', value: 'SC' },
  ];

  const tiposSanguineos = [
    { label: 'A+', value: 'A+' },
    { label: 'A-', value: 'A-' },
    { label: 'B+', value: 'B+' },
    { label: 'B-', value: 'B-' },
    { label: 'AB+', value: 'AB+' },
    { label: 'AB-', value: 'AB-' },
    { label: 'O+', value: 'O+' },
    { label: 'O-', value: 'O-' },
  ];

  const comoSoubeOptions = [
    { label: 'Redes Sociais', value: 'Redes Sociais' },
    { label: 'Site do Support Fire', value: 'Site do Support Fire' },
    { label: 'Indicação de Amigo', value: 'Indicação de Amigo' },
    { label: 'Escola', value: 'Escola' },
    { label: 'Corpo de Bombeiros', value: 'Corpo de Bombeiros' },
    { label: 'Outros', value: 'Outros' },
  ];

  const validateForm = () => {
    const newErrors = {};
    
    if (!formData.nome.trim()) newErrors.nome = 'Nome é obrigatório';
    if (!formData.email.trim()) newErrors.email = 'E-mail é obrigatório';
    else if (!/\S+@\S+\.\S+/.test(formData.email)) newErrors.email = 'E-mail inválido';
    if (!formData.telefone.trim()) newErrors.telefone = 'Telefone é obrigatório';
    if (!formData.sexo) newErrors.sexo = 'Sexo é obrigatório';
    if (!formData.tipoSanguineo) newErrors.tipoSanguineo = 'Tipo sanguíneo é obrigatório';
    if (!formData.endereco.trim()) newErrors.endereco = 'Endereço é obrigatório';
    if (!formData.bairro.trim()) newErrors.bairro = 'Bairro é obrigatório';
    if (!formData.cidade.trim()) newErrors.cidade = 'Cidade é obrigatória';
    if (!formData.estado) newErrors.estado = 'Estado é obrigatório';
    if (!formData.dataNascimento) newErrors.dataNascimento = 'Data de nascimento é obrigatória';
    if (!formData.pai.trim()) newErrors.pai = 'Nome do pai é obrigatório';
    if (!formData.mae.trim()) newErrors.mae = 'Nome da mãe é obrigatório';
    if (!formData.comoSoube) newErrors.comoSoube = 'Como soube do curso é obrigatório';

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async () => {
    if (!validateForm()) {
      Alert.alert('Erro', 'Por favor, preencha todos os campos obrigatórios.');
      return;
    }

    try {
      // Gerar ID único
      const id = Date.now().toString();
      const registrationData = {
        id,
        ...formData,
        dataCadastro: new Date().toISOString(),
      };

      // Salvar no AsyncStorage
      const existingData = await AsyncStorage.getItem('registrations');
      const registrations = existingData ? JSON.parse(existingData) : [];
      registrations.push(registrationData);
      await AsyncStorage.setItem('registrations', JSON.stringify(registrations));

      Alert.alert(
        'Sucesso!', 
        'Pré-inscrição realizada com sucesso!',
        [
          {
            text: 'OK',
            onPress: () => navigation.navigate('Success', { registrationData })
          }
        ]
      );
    } catch (error) {
      Alert.alert('Erro', 'Ocorreu um erro ao salvar os dados. Tente novamente.');
    }
  };

  return (
    <SafeAreaView style={styles.container}>
      <ScrollView contentContainerStyle={styles.scrollContent}>
        <Card style={styles.card}>
          <Card.Content>
            <Title>Faça sua Pré-Inscrição</Title>
            <Paragraph>Preencha todos os campos obrigatórios (*)</Paragraph>
          </Card.Content>
        </Card>

        <Card style={styles.formCard}>
          <Card.Content>
            <TextInput
              label="Nome *"
              value={formData.nome}
              onChangeText={(text) => setFormData({...formData, nome: text})}
              error={!!errors.nome}
              style={styles.input}
            />
            <HelperText type="error" visible={!!errors.nome}>
              {errors.nome}
            </HelperText>

            <TextInput
              label="E-mail *"
              value={formData.email}
              onChangeText={(text) => setFormData({...formData, email: text})}
              keyboardType="email-address"
              error={!!errors.email}
              style={styles.input}
            />
            <HelperText type="error" visible={!!errors.email}>
              {errors.email}
            </HelperText>

            <TextInput
              label="Telefone *"
              value={formData.telefone}
              onChangeText={(text) => setFormData({...formData, telefone: text})}
              keyboardType="phone-pad"
              error={!!errors.telefone}
              style={styles.input}
            />
            <HelperText type="error" visible={!!errors.telefone}>
              {errors.telefone}
            </HelperText>

            <Title style={styles.sectionTitle}>Sexo *</Title>
            <View style={styles.radioContainer}>
              <RadioButton
                value="masculino"
                status={formData.sexo === 'masculino' ? 'checked' : 'unchecked'}
                onPress={() => setFormData({...formData, sexo: 'masculino'})}
              />
              <Title style={styles.radioLabel}>Masculino</Title>
            </View>
            <View style={styles.radioContainer}>
              <RadioButton
                value="feminino"
                status={formData.sexo === 'feminino' ? 'checked' : 'unchecked'}
                onPress={() => setFormData({...formData, sexo: 'feminino'})}
              />
              <Title style={styles.radioLabel}>Feminino</Title>
            </View>
            <HelperText type="error" visible={!!errors.sexo}>
              {errors.sexo}
            </HelperText>

            <Title style={styles.sectionTitle}>Tipo Sanguíneo *</Title>
            <RNPickerSelect
              onValueChange={(value) => setFormData({...formData, tipoSanguineo: value})}
              items={tiposSanguineos}
              placeholder={{ label: 'Selecione o tipo sanguíneo', value: null }}
              style={styles.picker}
            />
            <HelperText type="error" visible={!!errors.tipoSanguineo}>
              {errors.tipoSanguineo}
            </HelperText>

            <TextInput
              label="Endereço *"
              value={formData.endereco}
              onChangeText={(text) => setFormData({...formData, endereco: text})}
              error={!!errors.endereco}
              style={styles.input}
            />
            <HelperText type="error" visible={!!errors.endereco}>
              {errors.endereco}
            </HelperText>

            <TextInput
              label="Bairro *"
              value={formData.bairro}
              onChangeText={(text) => setFormData({...formData, bairro: text})}
              error={!!errors.bairro}
              style={styles.input}
            />
            <HelperText type="error" visible={!!errors.bairro}>
              {errors.bairro}
            </HelperText>

            <TextInput
              label="Cidade *"
              value={formData.cidade}
              onChangeText={(text) => setFormData({...formData, cidade: text})}
              error={!!errors.cidade}
              style={styles.input}
            />
            <HelperText type="error" visible={!!errors.cidade}>
              {errors.cidade}
            </HelperText>

            <Title style={styles.sectionTitle}>Estado *</Title>
            <RNPickerSelect
              onValueChange={(value) => setFormData({...formData, estado: value})}
              items={estados}
              placeholder={{ label: 'Selecione o estado', value: null }}
              style={styles.picker}
            />
            <HelperText type="error" visible={!!errors.estado}>
              {errors.estado}
            </HelperText>

            <Title style={styles.sectionTitle}>Data de Nascimento *</Title>
            <Button 
              mode="outlined" 
              onPress={() => setShowDatePicker(true)}
              style={styles.dateButton}
            >
              {formData.dataNascimento || 'Selecionar Data'}
            </Button>
            <HelperText type="error" visible={!!errors.dataNascimento}>
              {errors.dataNascimento}
            </HelperText>

            <TextInput
              label="Nome do Pai *"
              value={formData.pai}
              onChangeText={(text) => setFormData({...formData, pai: text})}
              error={!!errors.pai}
              style={styles.input}
            />
            <HelperText type="error" visible={!!errors.pai}>
              {errors.pai}
            </HelperText>

            <TextInput
              label="Nome da Mãe *"
              value={formData.mae}
              onChangeText={(text) => setFormData({...formData, mae: text})}
              error={!!errors.mae}
              style={styles.input}
            />
            <HelperText type="error" visible={!!errors.mae}>
              {errors.mae}
            </HelperText>

            <TextInput
              label="Curso Desejado *"
              value={formData.cursoDesejado}
              editable={false}
              style={styles.input}
            />

            <Title style={styles.sectionTitle}>Como soube do curso? *</Title>
            <RNPickerSelect
              onValueChange={(value) => setFormData({...formData, comoSoube: value})}
              items={comoSoubeOptions}
              placeholder={{ label: 'Selecione uma opção', value: null }}
              style={styles.picker}
            />
            <HelperText type="error" visible={!!errors.comoSoube}>
              {errors.comoSoube}
            </HelperText>

            <Button 
              mode="contained" 
              onPress={handleSubmit}
              style={styles.submitButton}
            >
              Enviar Pré-Inscrição
            </Button>
          </Card.Content>
        </Card>
      </ScrollView>

      <DatePicker
        modal
        open={showDatePicker}
        date={formData.dataNascimento ? new Date(formData.dataNascimento) : new Date()}
        mode="date"
        onConfirm={(date) => {
          setShowDatePicker(false);
          setFormData({...formData, dataNascimento: date.toISOString().split('T')[0]});
        }}
        onCancel={() => {
          setShowDatePicker(false);
        }}
      />
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f5f5f5',
  },
  scrollContent: {
    padding: 16,
  },
  card: {
    marginBottom: 16,
    elevation: 4,
  },
  formCard: {
    elevation: 4,
  },
  input: {
    marginBottom: 8,
  },
  sectionTitle: {
    fontSize: 16,
    marginTop: 16,
    marginBottom: 8,
  },
  radioContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 8,
  },
  radioLabel: {
    fontSize: 16,
    marginLeft: 8,
  },
  picker: {
    inputIOS: {
      fontSize: 16,
      paddingVertical: 12,
      paddingHorizontal: 10,
      borderWidth: 1,
      borderColor: '#ccc',
      borderRadius: 4,
      color: 'black',
      paddingRight: 30,
    },
    inputAndroid: {
      fontSize: 16,
      paddingHorizontal: 10,
      paddingVertical: 8,
      borderWidth: 1,
      borderColor: '#ccc',
      borderRadius: 4,
      color: 'black',
      paddingRight: 30,
    },
  },
  dateButton: {
    marginBottom: 8,
  },
  submitButton: {
    marginTop: 20,
    paddingVertical: 8,
  },
});

export default RegisterScreen;

