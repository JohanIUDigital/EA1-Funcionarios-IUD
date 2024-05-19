package edu.iud.ea1.func.iud.view;

import edu.iud.ea1.func.iud.controller.EstCivilController;
import edu.iud.ea1.func.iud.controller.FuncionarioController;
import edu.iud.ea1.func.iud.controller.GeneroController;
import edu.iud.ea1.func.iud.controller.TiposDocController;
import edu.iud.ea1.func.iud.domain.EstadoCivil;
import edu.iud.ea1.func.iud.domain.Funcionario;
import edu.iud.ea1.func.iud.domain.Genero;
import edu.iud.ea1.func.iud.domain.TipoDocumento;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class InitFormEA1FuncionariosIUD extends javax.swing.JFrame {

    private final FuncionarioController funcionarioController;
    private final EstCivilController estadoCivilController;
    private final GeneroController generoController;
    private final TiposDocController tiposDocController;

    private static final String[] COLUMNS = {"Identificación", "Nombre Completo", "Dirección", "Teléfono", "Género", "Estado Civil", "Fecha Nacimiento"};
    private static final String[] COLUMNS2 = {"Nombre Completo", "Parentesco", "Género", "Teléfono", "Dirección"};
    private static final String[] COLUMNS3 = {"Universidad", "Nivel de Estudio", "Título"};
    private static final String SELECCIONE = "--Seleccione--";

    public InitFormEA1FuncionariosIUD() {
        funcionarioController = new FuncionarioController();
        estadoCivilController = new EstCivilController();
        generoController = new GeneroController();
        tiposDocController = new TiposDocController();
        initComponents();
        listarFuncionarios();
        listarEstCiviles();
        listarGeneros();
        listarTiposDoc();
        addListener();
    }

    private void listarTiposDoc() {
        try {
            crTdocCmb.removeAllItems();
            TipoDocumento itemNone = new TipoDocumento();
            itemNone.setId_tipo_doc(SELECCIONE);
            itemNone.setTxt_tipo_doc("");
            crTdocCmb.addItem(itemNone);
            List<TipoDocumento> tiposDoc = tiposDocController.contGetTipoDoc();
            if (tiposDoc.isEmpty()) {
                return;
            }
            for (TipoDocumento tipoDoc : tiposDoc) {
                crTdocCmb.addItem(tipoDoc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listarGeneros() {
        try {
            upGenCmb.removeAllItems();
            Genero itemNone = new Genero();
            itemNone.setId_genero(SELECCIONE);
            itemNone.setTxt_genero("");
            upGenCmb.addItem(itemNone);
            crGenCmb.addItem(itemNone);
            crGenFamCmb.addItem(itemNone);
            List<Genero> generos = generoController.contGetGenero();
            if (generos.isEmpty()) {
                return;
            }
            for (Genero genero : generos) {
                upGenCmb.addItem(genero);
                crGenCmb.addItem(genero);
                crGenFamCmb.addItem(genero);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listarEstCiviles() {
        try {
            upEstCivCmb.removeAllItems();
            EstadoCivil itemNone = new EstadoCivil();
            itemNone.setId_est_civil(SELECCIONE);
            itemNone.setTxt_est_civil("");
            upEstCivCmb.addItem(itemNone);
            crEstCivCmb.addItem(itemNone);
            List<EstadoCivil> estadosCiviles = estadoCivilController.contGetEstCiviles();
            if (estadosCiviles.isEmpty()) {
                return;
            }
            for (EstadoCivil estadoCivil : estadosCiviles) {
                upEstCivCmb.addItem(estadoCivil);
                crEstCivCmb.addItem(estadoCivil);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listarFuncionarios() {
        try {
            upFuncCmb.removeAllItems();
            Funcionario itemNone = new Funcionario();
            itemNone.setTipoDocumento(SELECCIONE);
            itemNone.setNumDocumento("");
            itemNone.setNombre("");
            itemNone.setApellido("");
            upFuncCmb.addItem(itemNone);
            DefaultTableModel defaultTableModel = new DefaultTableModel();
            for (String COLUMN : COLUMNS) {
                defaultTableModel.addColumn(COLUMN);
            }
            tblFiltFunc.setModel(defaultTableModel);
            List<Funcionario> funcionarios = funcionarioController.contGetFuncionario();
            if (funcionarios.isEmpty()) {
                return;
            }

            defaultTableModel.setRowCount(funcionarios.size());
            int row = 0;

            for (Funcionario func : funcionarios) {
                defaultTableModel.setValueAt(func.getTipoDocumento() + " - " + func.getNumDocumento(), row, 0);
                defaultTableModel.setValueAt(func.getNombre() + " " + func.getApellido(), row, 1);
                defaultTableModel.setValueAt(func.getDireccion(), row, 2);
                defaultTableModel.setValueAt(func.getTelefono(), row, 3);
                defaultTableModel.setValueAt(func.getIdGenero() + " - " + func.getGenero(), row, 4);
                defaultTableModel.setValueAt(func.getIdEstadoCivil() + " - " + func.getEstadoCivil(), row, 5);
                defaultTableModel.setValueAt(func.getFechaNacimiento(), row, 6);
                row++;
                upFuncCmb.addItem(func);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void filtrarFuncionarios(String filNombre, String filNumDoc) {
        try {

            DefaultTableModel defaultTableModel = new DefaultTableModel();
            for (String COLUMN : COLUMNS) {
                defaultTableModel.addColumn(COLUMN);
            }
            System.out.println("filtrar1");
            tblFiltFunc.setModel(defaultTableModel);
            System.out.println("filtrar2");
            List<Funcionario> funcionarios = funcionarioController.contGetFuncionario(filNombre, filNumDoc);
            System.out.println("filtrar3");
            if (funcionarios.isEmpty()) {
                return;
            }

            System.out.println("filtrar4");
            defaultTableModel.setRowCount(funcionarios.size());
            int row = 0;

            System.out.println("filtrar1");
            for (Funcionario func : funcionarios) {
                defaultTableModel.setValueAt(func.getTipoDocumento() + " - " + func.getNumDocumento(), row, 0);
                defaultTableModel.setValueAt(func.getNombre() + " " + func.getApellido(), row, 1);
                defaultTableModel.setValueAt(func.getDireccion(), row, 2);
                defaultTableModel.setValueAt(func.getTelefono(), row, 3);
                defaultTableModel.setValueAt(func.getIdGenero() + " - " + func.getGenero(), row, 4);
                defaultTableModel.setValueAt(func.getIdEstadoCivil() + " - " + func.getEstadoCivil(), row, 5);
                defaultTableModel.setValueAt(func.getFechaNacimiento(), row, 6);
                row++;
                upFuncCmb.addItem(func);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addListener() {
        upFuncCmb.addItemListener(event -> {
            Funcionario selectedFunc = (Funcionario) event.getItem();
            if (selectedFunc.getTipoDocumento().equals(SELECCIONE)) {
                upIdFuncInp.setText("");
                upTipoDocInp.setText("");
                upNumDocInp.setText("");
                upNomInp.setText("");
                upApeInp.setText("");
                upDirInp.setText("");
                upTelInp.setText("");
                upFecNacInp.setText("");
                upGenCmb.setSelectedIndex(0);
                upEstCivCmb.setSelectedIndex(0);
            } else {
                upIdFuncInp.setText(String.valueOf(selectedFunc.getId()));
                upTipoDocInp.setText(selectedFunc.getTipoDocumento());
                upNumDocInp.setText(selectedFunc.getNumDocumento());
                upNomInp.setText(selectedFunc.getNombre());
                upApeInp.setText(selectedFunc.getApellido());
                upDirInp.setText(selectedFunc.getDireccion());
                upTelInp.setText(selectedFunc.getTelefono());
                upFecNacInp.setText(String.valueOf(selectedFunc.getFechaNacimiento()));
                Genero genero = new Genero();
                genero.setId_genero(selectedFunc.getIdGenero());
                genero.setTxt_genero(selectedFunc.getGenero());
                //upGenCmb.setSelectedIndex(1);
                //upEstCivCmb.setSelectedIndex(1);
            }
        });
    }

    private boolean validBeforeSave() {
        boolean isValid = true;
        if (crNomInp.getText().trim().length() == 0) {
            crNomInp.requestFocus();
            isValid = false;
        }
        if (crApeInp.getText().trim().length() == 0) {
            crApeInp.requestFocus();
            isValid = false;
        }
        if (crDirInp.getText().trim().length() == 0) {
            crDirInp.requestFocus();
            isValid = false;
        }
        if (crNdocInp.getText().trim().length() == 0) {
            crNdocInp.requestFocus();
            isValid = false;
        }
        if (crTelInp.getText().trim().length() == 0) {
            crTelInp.requestFocus();
            isValid = false;
        }
        if (crFecNacInp.getText().trim().length() == 0) {
            crFecNacInp.requestFocus();
            isValid = false;
        }
        if (crTdocCmb.getSelectedIndex() == 0) {
            crTdocCmb.requestFocus();
            isValid = false;
        }
        if (crGenCmb.getSelectedIndex() == 0) {
            crGenCmb.requestFocus();
            isValid = false;
        }
        if (crEstCivCmb.getSelectedIndex() == 0) {
            crEstCivCmb.requestFocus();
            isValid = false;
        }
        return isValid;
    }

    public boolean validBeforeUpdate() {
        boolean isValid = true;
        if (upNomInp.getText().trim().length() == 0) {
            upNomInp.requestFocus();
            isValid = false;
        }
        if (upApeInp.getText().trim().length() == 0) {
            upApeInp.requestFocus();
            isValid = false;
        }
        if (upDirInp.getText().trim().length() == 0) {
            upDirInp.requestFocus();
            isValid = false;
        }
        if (upTelInp.getText().trim().length() == 0) {
            upTelInp.requestFocus();
            isValid = false;
        }
        if (upGenCmb.getSelectedIndex() == 0) {
            upGenCmb.requestFocus();
            isValid = false;
        }
        if (upEstCivCmb.getSelectedIndex() == 0) {
            upEstCivCmb.requestFocus();
            isValid = false;
        }
        return isValid;
    }

    public Date formatterDate(String sFecha) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dFecha = formato.parse(sFecha);
            System.out.println("Fecha convertida: " + dFecha);
            return dFecha;
        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido");
            e.printStackTrace();
            return null;
        }
    }

    public void clearFieldsCreate() {
        crNomInp.setText("");
        crApeInp.setText("");
        crNdocInp.setText("");
        crDirInp.setText("");
        crTelInp.setText("");
        crFecNacInp.setText("");
        crFecNacInp.setText("");
        crTdocCmb.setSelectedIndex(0);
        crGenCmb.setSelectedIndex(0);
        crGenCmb.setSelectedIndex(0);
        crEstCivCmb.setSelectedIndex(0);
        clearFieldsFamily();
        tblCrFam.removeAll();
    }

    public void clearFieldsFamily() {
        crFmNomInp.setText("");
        crFmRolInp.setText("");
        crGenFamCmb.setSelectedIndex(0);
        crFmTelInp.setText("");
        crFmDirInp.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        crNdocInp = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCrAca = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        crApeInp = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        crGenFamCmb = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        crTdocCmb = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        crTelInp = new javax.swing.JTextPane();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        crNomInp = new javax.swing.JTextPane();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        crEstCivCmb = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        crFecNacInp = new javax.swing.JTextPane();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        crFmNomInp = new javax.swing.JTextPane();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        crDirInp = new javax.swing.JTextPane();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        crAcaTitInp = new javax.swing.JTextPane();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        crFmDirInp = new javax.swing.JTextPane();
        btnAddFam = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        crFmTelInp = new javax.swing.JTextPane();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblCrFam = new javax.swing.JTable();
        btnAddAca = new javax.swing.JButton();
        btnCrearRegistro = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane25 = new javax.swing.JScrollPane();
        crFmRolInp = new javax.swing.JTextPane();
        crGenCmb = new javax.swing.JComboBox<>();
        uniIpn = new javax.swing.JTextField();
        lvlStudy = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblFiltFunc = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btnFiltrar = new javax.swing.JButton();
        jScrollPane15 = new javax.swing.JScrollPane();
        filNomInp = new javax.swing.JTextPane();
        jScrollPane16 = new javax.swing.JScrollPane();
        filNumInp = new javax.swing.JTextPane();
        btnLimFiltro = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        upFuncCmb = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        upIdFuncInp = new javax.swing.JTextPane();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        upNumDocInp = new javax.swing.JTextPane();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        upNomInp = new javax.swing.JTextPane();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        upApeInp = new javax.swing.JTextPane();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        upDirInp = new javax.swing.JTextPane();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane22 = new javax.swing.JScrollPane();
        upTelInp = new javax.swing.JTextPane();
        jLabel33 = new javax.swing.JLabel();
        upGenCmb = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        upEstCivCmb = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane23 = new javax.swing.JScrollPane();
        upFecNacInp = new javax.swing.JTextPane();
        jScrollPane24 = new javax.swing.JScrollPane();
        upTipoDocInp = new javax.swing.JTextPane();
        jSeparator2 = new javax.swing.JSeparator();
        btnActualizarFuncionario = new javax.swing.JButton();
        btnEliminarFuncionario = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("*Nombres:");

        jScrollPane1.setViewportView(crNdocInp);

        jScrollPane2.setViewportView(tblCrAca);

        jLabel2.setText("*Dirección:");

        jScrollPane3.setViewportView(crApeInp);

        jLabel3.setText("*Apellidos:");

        jLabel4.setText("*Género:");

        crTdocCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crTdocCmbActionPerformed(evt);
            }
        });

        jLabel5.setText("*Tipo de documento:");

        jScrollPane4.setViewportView(crTelInp);

        jLabel6.setText("*Teléfono:");

        jLabel7.setText("*Núm de documento:");

        jScrollPane5.setViewportView(crNomInp);

        jLabel8.setText("*Fecha de Nacimiento:");

        jLabel9.setText("*Estado Civil:");

        jScrollPane7.setViewportView(crFecNacInp);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Información Grupo Familiar:");

        jLabel11.setText("Nombre Completo:");

        jScrollPane9.setViewportView(crFmNomInp);

        jLabel12.setText("Parentesco:");

        jLabel13.setText("teléfono:");

        jScrollPane8.setViewportView(crDirInp);

        jLabel14.setText("dirección:");

        jScrollPane10.setViewportView(crAcaTitInp);

        jLabel15.setText("Género:");

        jScrollPane11.setViewportView(crFmDirInp);

        btnAddFam.setBackground(new java.awt.Color(204, 204, 255));
        btnAddFam.setText("AGREGAR FAMILIAR");
        btnAddFam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFamActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setText("Información Acádemica:");

        jLabel18.setText("Universidad:");

        jLabel19.setText("Título:");

        jLabel20.setText("Nivel de estudio:");

        jScrollPane12.setViewportView(crFmTelInp);

        jScrollPane13.setViewportView(tblCrFam);

        btnAddAca.setBackground(new java.awt.Color(255, 204, 255));
        btnAddAca.setText("AGREGAR ESTUDIO");
        btnAddAca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAcaActionPerformed(evt);
            }
        });

        btnCrearRegistro.setBackground(new java.awt.Color(255, 204, 255));
        btnCrearRegistro.setText("CREAR REGISTRO");
        btnCrearRegistro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCrearRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearRegistroActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setText("Información Principal:");

        jScrollPane25.setViewportView(crFmRolInp);

        lvlStudy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lvlStudyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane5))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(crTdocCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(crGenCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(crEstCivCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4))))
                .addGap(376, 376, 376))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel13)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                            .addComponent(jScrollPane12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(82, 82, 82)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(crGenFamCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnAddFam)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 1127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(290, 290, 290)
                        .addComponent(btnCrearRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddAca)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(uniIpn, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lvlStudy, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addComponent(jLabel22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(237, 237, 237)
                        .addComponent(jLabel10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(jLabel17)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(384, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(crTdocCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6))
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(crEstCivCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel8))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(crGenCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(jLabel15)
                                .addComponent(crGenFamCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(btnAddFam)))
                                .addGap(208, 208, 208)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel18)
                                .addComponent(uniIpn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel19)
                                .addComponent(jLabel20))
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lvlStudy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddAca)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCrearRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(334, 334, 334)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(376, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Crear Registro Funcionario", jPanel1);

        tblFiltFunc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane14.setViewportView(tblFiltFunc);

        jLabel16.setText("Buscar por Nombre:");

        jLabel21.setText("Buscar por Núm de Documento:");

        btnFiltrar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        jScrollPane15.setViewportView(filNomInp);

        jScrollPane16.setViewportView(filNumInp);

        btnLimFiltro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLimFiltro.setText("Limpiar Filtro");
        btnLimFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimFiltroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimFiltro))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                        .addComponent(jScrollPane15)))
                .addContainerGap(667, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFiltrar)
                    .addComponent(btnLimFiltro))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(268, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consultar Funcionario", jPanel2);

        jLabel23.setText("Seleccione Funcionario:");

        upFuncCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                upFuncCmbItemStateChanged(evt);
            }
        });
        upFuncCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upFuncCmbActionPerformed(evt);
            }
        });

        upIdFuncInp.setEditable(false);
        upIdFuncInp.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane17.setViewportView(upIdFuncInp);

        jLabel26.setText("Número de Documento:");

        upNumDocInp.setEditable(false);
        upNumDocInp.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane19.setViewportView(upNumDocInp);

        jLabel27.setText("Nombres:");

        jScrollPane20.setViewportView(upNomInp);

        jLabel28.setText("Tipo Documento:");

        jLabel29.setText("Id funcionario:");

        jLabel30.setText("Apellidos:");

        jScrollPane18.setViewportView(upApeInp);

        jLabel31.setText("Dirección:");

        jScrollPane21.setViewportView(upDirInp);

        jLabel32.setText("Teléfono:");

        jScrollPane22.setViewportView(upTelInp);

        jLabel33.setText("Género:");

        jLabel34.setText("Estado Civil:");

        upEstCivCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upEstCivCmbActionPerformed(evt);
            }
        });

        jLabel35.setText("Fecha de Nacimiento:");

        upFecNacInp.setEditable(false);
        upFecNacInp.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane23.setViewportView(upFecNacInp);

        upTipoDocInp.setEditable(false);
        upTipoDocInp.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane24.setViewportView(upTipoDocInp);

        btnActualizarFuncionario.setBackground(new java.awt.Color(255, 204, 255));
        btnActualizarFuncionario.setText("ACTUALIZAR FUNCIONARIO");
        btnActualizarFuncionario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnActualizarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarFuncionarioActionPerformed(evt);
            }
        });

        btnEliminarFuncionario.setBackground(new java.awt.Color(255, 51, 51));
        btnEliminarFuncionario.setText("ELIMINAR FUNCIONARIO");
        btnEliminarFuncionario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEliminarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarFuncionarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel35)
                    .addComponent(jLabel34)
                    .addComponent(jLabel33)
                    .addComponent(jLabel32)
                    .addComponent(jLabel31)
                    .addComponent(jLabel30)
                    .addComponent(jLabel27)
                    .addComponent(jLabel25)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel24)
                            .addGap(83, 83, 83))
                        .addComponent(jLabel23))
                    .addComponent(jLabel28)
                    .addComponent(jLabel26)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnActualizarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(upFuncCmb, 0, 362, Short.MAX_VALUE)
                        .addComponent(jScrollPane17)
                        .addComponent(jScrollPane19, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane20)
                        .addComponent(jScrollPane18)
                        .addComponent(jScrollPane21)
                        .addComponent(jScrollPane22)
                        .addComponent(upGenCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(upEstCivCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane24, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane23, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(515, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(upFuncCmb))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel29))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(jLabel28))
                    .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(upGenCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(upEstCivCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(299, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Modificar/Eliminar Funcionario", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 879, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearRegistroActionPerformed
        boolean isValid = validBeforeSave();
        if (!isValid) {
            JOptionPane.showMessageDialog(null, "Favor Completar los Campos Obligatorios (*)");
        } else {

            int option = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea Crear el nuevo funcionario ?", "Crear Funcionario", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == 0) {
                try {
                    Funcionario funcionario = new Funcionario();
                    funcionario.setNombre(crNomInp.getText().trim());
                    funcionario.setApellido(crApeInp.getText().trim());
                    funcionario.setNumDocumento(crNdocInp.getText().trim());
                    funcionario.setDireccion(crDirInp.getText().trim());
                    funcionario.setTelefono(crTelInp.getText().trim());
                    System.out.println(formatterDate(crFecNacInp.getText().trim()));
                    funcionario.setFechaNacimiento(formatterDate(crFecNacInp.getText().trim()));
                    funcionario.setTipoDocumento(crTdocCmb.getSelectedItem().toString().substring(0, 3).trim());
                    funcionario.setIdGenero(crGenCmb.getSelectedItem().toString().substring(0, 2).trim());
                    funcionario.setIdEstadoCivil(crEstCivCmb.getSelectedItem().toString().substring(0, 1).trim());
                    funcionarioController.contCreateFuncionario(funcionario);
                    clearFieldsCreate();
                    listarFuncionarios();
                    JOptionPane.showMessageDialog(null, "Funcionario Creado con éxito");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }


    }//GEN-LAST:event_btnCrearRegistroActionPerformed

    private void btnAddAcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAcaActionPerformed
        // TODO add your handling code here:
        
        tblCrAca.getModel().setValueAt("", 0, 1);
        tblCrAca.getModel().setValueAt("", 0, 2);
        tblCrAca.getModel().setValueAt("", 0, 3);
        tblCrAca.getModel().setValueAt("", 0, 4);
        tblCrAca.getModel().setValueAt("", 0, 5);
        
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        for (String COLUMN : COLUMNS3) {
            defaultTableModel.addColumn(COLUMN);
        }
        String universidad = uniIpn.getText();
        String nivelStud = lvlStudy.getText();
        String titulo = crAcaTitInp.getText();
        defaultTableModel.addRow(new Object[]{universidad, nivelStud, titulo});
        tblCrAca.setModel(defaultTableModel);
    }//GEN-LAST:event_btnAddAcaActionPerformed

    private void btnAddFamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFamActionPerformed
        
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        for (String COLUMN : COLUMNS2) {
            defaultTableModel.addColumn(COLUMN);
        }
        String nombre = crFmNomInp.getText();
        String rol = crFmRolInp.getText();
        String genero = crGenFamCmb.getSelectedItem().toString();
        String telefono = crFmTelInp.getText();
        String direccion = crFmDirInp.getText();
        defaultTableModel.addRow(new Object[]{nombre, rol, genero, telefono, direccion});
        tblCrFam.setModel(defaultTableModel);
        clearFieldsFamily();
    }//GEN-LAST:event_btnAddFamActionPerformed

    private void btnLimFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimFiltroActionPerformed
        // TODO add your handling code here:
        filNomInp.setText("");
        filNumInp.setText("");
        listarFuncionarios();

    }//GEN-LAST:event_btnLimFiltroActionPerformed

    private void btnActualizarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarFuncionarioActionPerformed
        // TODO add your handling code here:
        boolean isValid = validBeforeUpdate();
        if (!isValid) {
            JOptionPane.showMessageDialog(null, "Favor Completar los Campos Obligatorios (*)");
        } else {

            int option = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea actualizar el funcionario " + upIdFuncInp.getText() + " ?", "Actualizar Funcionario", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == 0) {
                try {
                    Funcionario funcionario = new Funcionario();
                    funcionario.setId(Integer.parseInt(upIdFuncInp.getText()));
                    funcionario.setNombre(upNomInp.getText().trim());
                    funcionario.setApellido(upApeInp.getText().trim());
                    funcionario.setDireccion(upDirInp.getText().trim());
                    funcionario.setTelefono(upTelInp.getText().trim());
                    funcionario.setIdGenero(upGenCmb.getSelectedItem().toString().substring(0, 2).trim());
                    funcionario.setIdEstadoCivil(upEstCivCmb.getSelectedItem().toString().substring(0, 1).trim());
                    funcionarioController.contUpdateFuncionario(funcionario.getId(), funcionario);
                    upFuncCmb.setSelectedIndex(0);
                    listarFuncionarios();
                    JOptionPane.showMessageDialog(null, "Funcionario Actualizado con éxito");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        }

    }//GEN-LAST:event_btnActualizarFuncionarioActionPerformed

    private void upFuncCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upFuncCmbActionPerformed

    }//GEN-LAST:event_upFuncCmbActionPerformed

    private void upEstCivCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upEstCivCmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_upEstCivCmbActionPerformed

    private void crTdocCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crTdocCmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_crTdocCmbActionPerformed

    private void upFuncCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_upFuncCmbItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_upFuncCmbItemStateChanged

    private void btnEliminarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarFuncionarioActionPerformed
        // TODO add your handling code here:
        if (upFuncCmb.getSelectedIndex() != 0) {
            int option = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea Eliminar el funcionario " + upIdFuncInp.getText() + " " + upNomInp.getText() + " ?", "Actualizar Funcionario", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == 0) {
                try {
                    funcionarioController.contDeleteFuncionario(Integer.parseInt(upIdFuncInp.getText()));
                    listarFuncionarios();
                    upFuncCmb.setSelectedIndex(0);
                    JOptionPane.showMessageDialog(null, "El Funcionario se Eliminó Correctamente");

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "No fue posible Eliminar el Funcionario");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar previamente un Funcionario para Eliminar");
        }
    }//GEN-LAST:event_btnEliminarFuncionarioActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        // TODO add your handling code here:
        String filNombre = filNomInp.getText();
        String filNumDoc = filNumInp.getText();
        System.out.println(filNombre + filNumDoc);
        filtrarFuncionarios(filNombre, filNumDoc);


    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void lvlStudyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lvlStudyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lvlStudyActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InitFormEA1FuncionariosIUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InitFormEA1FuncionariosIUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InitFormEA1FuncionariosIUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InitFormEA1FuncionariosIUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InitFormEA1FuncionariosIUD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarFuncionario;
    private javax.swing.JButton btnAddAca;
    private javax.swing.JButton btnAddFam;
    private javax.swing.JButton btnCrearRegistro;
    private javax.swing.JButton btnEliminarFuncionario;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnLimFiltro;
    private javax.swing.JTextPane crAcaTitInp;
    private javax.swing.JTextPane crApeInp;
    private javax.swing.JTextPane crDirInp;
    private javax.swing.JComboBox<EstadoCivil> crEstCivCmb;
    private javax.swing.JTextPane crFecNacInp;
    private javax.swing.JTextPane crFmDirInp;
    private javax.swing.JTextPane crFmNomInp;
    private javax.swing.JTextPane crFmRolInp;
    private javax.swing.JTextPane crFmTelInp;
    private javax.swing.JComboBox<Genero> crGenCmb;
    private javax.swing.JComboBox<Genero> crGenFamCmb;
    private javax.swing.JTextPane crNdocInp;
    private javax.swing.JTextPane crNomInp;
    private javax.swing.JComboBox<TipoDocumento> crTdocCmb;
    private javax.swing.JTextPane crTelInp;
    private javax.swing.JTextPane filNomInp;
    private javax.swing.JTextPane filNumInp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField lvlStudy;
    private javax.swing.JTable tblCrAca;
    private javax.swing.JTable tblCrFam;
    private javax.swing.JTable tblFiltFunc;
    private javax.swing.JTextField uniIpn;
    private javax.swing.JTextPane upApeInp;
    private javax.swing.JTextPane upDirInp;
    private javax.swing.JComboBox<EstadoCivil> upEstCivCmb;
    private javax.swing.JTextPane upFecNacInp;
    private javax.swing.JComboBox<Funcionario> upFuncCmb;
    private javax.swing.JComboBox<Genero> upGenCmb;
    private javax.swing.JTextPane upIdFuncInp;
    private javax.swing.JTextPane upNomInp;
    private javax.swing.JTextPane upNumDocInp;
    private javax.swing.JTextPane upTelInp;
    private javax.swing.JTextPane upTipoDocInp;
    // End of variables declaration//GEN-END:variables
}
