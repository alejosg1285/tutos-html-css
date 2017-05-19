package com.example.cliente.udemyandroidcero;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.cliente.udemyandroidcero.Activities.ListViewActivity;
import com.example.cliente.udemyandroidcero.Activities.RealmActivity;
import com.example.cliente.udemyandroidcero.Activities.RecyclerViewActivity;

public class MainActivity extends AppCompatActivity
{

    private EditText textPhone, textWeb;
    private ImageButton btnCamara, btnPhone, btnWeb;

    private final int CALL_PHONE_CODE = 100;
    private final int PICTURE_FROM_CAMERA = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_checklist);

        textPhone = (EditText) findViewById(R.id.textPhone);
        textWeb = (EditText) findViewById(R.id.textWeb);

        //Evento del boton de telefono.
        btnPhone = (ImageButton) findViewById(R.id.btnPhone);
        btnPhone.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String phoneNumner = textPhone.getText().toString();
                if(phoneNumner != null && !phoneNumner.isEmpty())
                {
                    //Comprobr si la varsion de android es igual o superior a Marshmallow
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    {
                        //Comprobar si  ha aceptado anteriormente el permiso.
                        if(CheckPermission(Manifest.permission.CALL_PHONE))
                        {
                            makeCallPhone();
                        } else
                        {
                            //No ha aceptado o es primer uso.
                            if(!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE))
                            {
                                //Primer vez que usa la aplicación.
                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_CODE);
                            } else
                            {
                                //Denego anteriormente el permiso.
                                Toast.makeText(MainActivity.this, "Por favor acepte la solicitud de permiso.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.addCategory(Intent.CATEGORY_DEFAULT);
                                intent.setData(Uri.parse("package:" + getPackageName()));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                startActivity(intent);
                            }
                        }
                    } else
                    {
                        OlderVersion(phoneNumner);
                    }
                } else
                {
                    Toast.makeText(MainActivity.this, "Ingrese un número de celular", Toast.LENGTH_SHORT).show();
                }
            }

            private void OlderVersion(String phoneNumber)
            {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));

                if(CheckPermission(Manifest.permission.CALL_PHONE))
                {
                    startActivity(intent);
                } else
                {
                    Toast.makeText(MainActivity.this, "Acceso denegado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Evento del boton de navegación web.
        btnWeb = (ImageButton) findViewById(R.id.btnWeb);
        btnWeb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String url = textWeb.getText().toString();
                if(url != null && !url.isEmpty())
                {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://" + url));
                    //Intent para abrir lista de contactos.
                    Intent contacts = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));
                    //Intent para enviar email rapido.
                    Intent email = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:alejosaa@mail.com"));
                    //Intent para enviar email completo.
                    Intent mail = new Intent(Intent.ACTION_VIEW, Uri.parse("alejosaaa@mail.com"));
                    mail.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                    mail.setType("plain/text");//mail.setType("message/rfc822");
                    mail.putExtra(Intent.EXTRA_SUBJECT, "Titulo del mail");
                    mail.putExtra(Intent.EXTRA_TEXT, "mensaje del mail");
                    mail.putExtra(Intent.EXTRA_EMAIL, new String[]{"lunita@mail.com", "orion@mail.com"});
                    startActivity(Intent.createChooser(mail, "Elije aplicacion."));
                    //Intent para marcar sin permitir permisos.
                    Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:32434333"));

                    //startActivity(intent);
                }
            }
        });

        btnCamara = (ImageButton) findViewById(R.id.btnCamara);
        btnCamara.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");

                startActivityForResult(intent, PICTURE_FROM_CAMERA);
            }
        });
    }

    //Crear menu lateral de la actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //Captura el elemento seleccionado en el menu.
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.opc1:
                Intent opc1 = new Intent(MainActivity.this, MainActivity.class);
                startActivity(opc1);
                return true;
            //break;
            case R.id.opc2:
                Intent opc2 = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(opc2);
                return true;
            //break;
            case R.id.opc3:
                Intent opc3 = new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(opc3);
                return true;
            case R.id.opc4:
                Intent intent=new Intent(MainActivity.this, RealmActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {

        switch(requestCode)
        {
            case CALL_PHONE_CODE:
                String permission = permissions[0];
                int result = grantResults[0];

                if(permission.equals(Manifest.permission.CALL_PHONE))
                {
                    if(result == PackageManager.PERMISSION_GRANTED)
                    {
                        makeCallPhone();
                    } else
                    {
                        Toast.makeText(MainActivity.this, "Acceso denegado", Toast.LENGTH_SHORT).show();
                    }
                } else
                {
                    Toast.makeText(MainActivity.this, "Acceso denegado", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch(requestCode)
        {
            case PICTURE_FROM_CAMERA:
                if(resultCode == Activity.RESULT_OK)
                {
                    String result = data.toUri(0);
                    Toast.makeText(MainActivity.this, "Resultado: " + result, Toast.LENGTH_SHORT).show();
                } else
                {
                    Toast.makeText(MainActivity.this, "Se ha generado un error, intentelo de nuevo", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    /**
     * Abre la aplicación para realizar la llamada telefonica.
     */
    private void makeCallPhone()
    {
        String phoneNumber = textPhone.getText().toString();
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        startActivity(intent);
    }

    private boolean CheckPermission(String permission)
    {
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}
