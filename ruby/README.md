Crea un proyecto de Rails API-only (cualquier versión de rails >= 4.2), usando postgres como base de datos.
El proyecto será una app con un CRUD de "pacientes" y un CRUD de "citas".

Crea el proyecto en un nuevo repositorio en tu cuenta de github.

## Schema:

`Appointment`
- cause (text)
- physician_id (belongs_to)
- patient (belongs_to)
- reminder (boolean)

`Physician`
- name
- email
- has_many appointments

`Patient`
- Name
- Email
- has_many appointments

## Funcionalidad:
La API tiene que poder hacer lo siguiente:
- CRUD appointments
- CRUD patients
- Al crear un appointment, mandar dos reminders vía email (usa la clase FakeReminder) al patient y al, physician; sólo si, `appointment.reminder = true`
- Reenviar los reminders

FakeReminder (incluye esta clase en el proyecto, refactorizala si es necesario)
```ruby
class FakeReminder
  def self.send_reminder(type, email, doctor_name, patient_name)
    if type.eql?('patient')
      puts "Sending email to #{email} ... remember your appointment with dr #{doctor_name}"
    else
      puts "Sending email to #{email} ... remember your appointment with the patient: #{patient_name}"
    end
  end
end
```

## Nice to have:
- Tests: Preferimos usar Rspec pero puedes usar cualquiera. Haz pruebas de toda la funcionalidad: modelos, controllers, etc.
- [Semantic commits](https://github.com/ecaresoft/ecs-tests/blob/master/doc/commits.md)
