package sample;

public class Tuning_studio
{
    private String id;// Номер заказа
    public String date;// Дата заказа
    public String fio;// ФИО заказчика
    public String car;// Автомобиль для доработок
    public String engine_mod;// Доработки двигателя
    public  String body_mod;// Доработки кузова
    public  String susp_mod; // Доработки ходовой части


    public Tuning_studio(String id,String date,String fio,String car, String engine_mod, String susp_mod,String body_mod)
    {
        this.id = new String(id);
        this.date = new String(date);
        this.body_mod = new String(body_mod);
        this.fio = new String(fio);
        this.car = new String(car);
        this.engine_mod = new String(engine_mod);
        this.susp_mod = new String(susp_mod);
    }

    public String getId()
    {
        return id;
    }

    public String getDate()
    {
        return date;
    }

    public String getCar()
    {
        return car;
    }

    public String getFio()
    {
        return fio;
    }

    public String getEngine_mod()
    {
        return engine_mod;
    }

    public String getBody_mod()
    {
        return body_mod;
    }
    public String getSusp_mod()
    {
        return susp_mod;
    }

    public void set_id(String id)
    {
        this.id = id;
    }
    public void set_date(String date)
    {
        this.date = date;
    }
    public void set_fio(String fio)
    {
        this.fio = fio;
    }
    public void set_car(String car)
    {
        this.car = car;
    }
    public void set_engine_mod(String engine_mod)
    {
        this.engine_mod = engine_mod;
    }
    public void set_body_mod(String body_mod)
    {
        this.body_mod = body_mod;
    }
    public void set_susp_mod(String susp_mod)
    {
        this.susp_mod = susp_mod;
    }
}

